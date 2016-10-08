package com.alaindroid.game.gog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alaindroid.game.gog.impl.GOGStandardAttackRule;
import com.alaindroid.game.gog.impl.MainDirectionMoveRule;
import com.alaindroid.game.gog.model.Vector;

public class GOGBoard {
	private GOGPiece[][] piecesMatrix;
	private int x, y;
	private AttackRule attackRule;
	private MoveRule moveRule;

	private Vector min;
	private Vector max;

	private Map<String, GOGPlayer> playersMap;
	private List<GOGPieceWithCoordinate> allPieces;

	private String currentPlayerId;

	public GOGBoard(int x, int y, AttackRule attackRule, MoveRule moveRule) {
		this.piecesMatrix = new GOGPiece[x][y];
		this.x = x;
		this.y = y;
		playersMap = new HashMap<String, GOGPlayer>();
		allPieces = new ArrayList<GOGPieceWithCoordinate>();
		min = new Vector(0, 0);
		max = new Vector(x, y);
	}

	public void addPlayer(GOGPlayer player) {
		playersMap.put(player.getId(), player);
	}

	public boolean isPlayer(String playerId) {
		return playersMap.get(playerId) != null;
	}

	public boolean addPiece(GOGPiece piece, Vector vector) {
		String playerId = piece.getPlayerId();
		if (!isPlayer(playerId)) {
			return false;
		}
		for (GOGPieceWithCoordinate boardPiece : allPieces) {
			if (vector.getX() == boardPiece.vector.getX() && vector.getY() == boardPiece.vector.getY()) {
				return false;
			}
		}
		GOGPieceWithCoordinate boardPiece = new GOGPieceWithCoordinate(playerId, piece, vector);
		allPieces.add(boardPiece);
		piecesMatrix[vector.getX()][vector.getY()] = piece;
		return true;
	}

	private void removePiece(GOGPieceWithCoordinate boardPiece) {
		piecesMatrix[boardPiece.vector.getX()][boardPiece.vector.getY()] = null;
		allPieces.remove(boardPiece);
	}

	private void updateCoordinate(GOGPieceWithCoordinate boardPiece, Vector newCoord) {
		piecesMatrix[newCoord.getX()][newCoord.getY()] = piecesMatrix[boardPiece.vector.getX()][boardPiece.vector
				.getY()];
		piecesMatrix[boardPiece.vector.getX()][boardPiece.vector.getY()] = null;
		boardPiece.vector = newCoord;

	}

	public MoveResult move(String playerId, Move move) {
		boolean isValid = moveRule.valid(move, min, max);
		if (!isValid) {
			return MoveResult.INVALID;
		}
		GOGPieceWithCoordinate toMove = null;
		GOGPieceWithCoordinate target = null;
		for (GOGPieceWithCoordinate boardPiece : allPieces) {
			if (boardPiece.vector.equals(move.from)) {
				if (boardPiece.playerId.equalsIgnoreCase(playerId)) {
					toMove = boardPiece;
				} else {
					return MoveResult.NOT_OWNED;
				}
			}
			if (boardPiece.vector.equals(move.to)) {
				if (boardPiece.playerId.equalsIgnoreCase(playerId)) {
					return MoveResult.BLOCKED;
				} else {
					target = boardPiece;
				}
			}

		}
		if (toMove == null) {
			return MoveResult.EMPTY_START;
		}
		if (target == null) {
			updateCoordinate(toMove, move.to);
		} else {
			GOGPiece piece = attackRule.getWinner(toMove.piece, target.piece);
			if (piece == toMove.piece) {
				removePiece(target);
				return MoveResult.ATTACK_SUCCESS;
			} else if (piece == target.piece) {
				removePiece(toMove);
				return MoveResult.ATTACK_FAIL;
			} else {
				removePiece(toMove);
				removePiece(target);
				return MoveResult.DRAW;
			}

		}
		return MoveResult.MOVE_SUCCESS;
	}

	public enum MoveResult {
		INVALID, NOT_OWNED, EMPTY_START, BLOCKED, ATTACK_SUCCESS, ATTACK_FAIL, DRAW, MOVE_SUCCESS;
	}

	private static class GOGPieceWithCoordinate {
		public GOGPieceWithCoordinate(String playerId, GOGPiece piece, Vector vector) {
			this.playerId = playerId;
			this.piece = piece;
			this.vector = vector;
		}

		String playerId;
		GOGPiece piece;
		Vector vector;
	}

	private static final int STR_LEN = 5;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Type[][] typeMatrix = getPieceMatrix(currentPlayerId);
		for (int x = typeMatrix.length - 1; x >= 0; x--) {
			Type[] row = typeMatrix[x];
			for (int y = 0; y < row.length; y++) {
				Type piece = row[y];
				sb.append("[");
				if (piece == null) {
					// padRightSpace(sb, STR_LEN);
					sb.append("~NIL~");
				} else {
					sb.append(piece.name());
					padRightSpace(sb, STR_LEN - piece.name().length());
				}
				sb.append("] ");
			}
			sb.append("\r\n");
		}

		return sb.toString();
	}

	private void padRightSpace(StringBuilder sb, int num) {
		for (int i = 0; i < num; i++) {
			sb.append(" ");
		}
	}

	public Type[][] getPieceMatrix(String playerId) {
		Type[][] retVal = new Type[x][y];
		for (int x = piecesMatrix.length - 1; x >= 0; x--) {
			GOGPiece[] row = piecesMatrix[x];
			for (int y = 0; y < row.length; y++) {
				GOGPiece piece = row[y];
				retVal[x][y] = getPieceType(playerId, x, y);
			}
		}
		return retVal;
	}

	public List<GOGPieceWithCoordinate> getPieces(String playerId) {
		List<GOGPieceWithCoordinate> pieces = new ArrayList<GOGPieceWithCoordinate>();
		for (GOGPieceWithCoordinate piece : allPieces) {
			if (playerId.equalsIgnoreCase(piece.playerId)) {
				pieces.add(piece);
			}
		}
		return pieces;
	}

	public Type getPieceType(String playerId, int x, int y) {
		GOGPiece piece = piecesMatrix[x][y];
		if (piece == null) {
			return null;
		}
		if (piece.getPlayerId().equalsIgnoreCase(playerId)) {
			return piece.getType();
		} else {
			return Type.INVI;
		}
	}

	public static void main(String[] test) {
		GOGBoard board = new GOGBoard(8, 9, GOGStandardAttackRule.getInstance(), MainDirectionMoveRule.getInstance());
		String player1Id = "mein1";
		board.addPlayer(new GOGPlayer(player1Id) {

			@Override
			public Move getMove(GOGBoard board) {
				return null;
			}
		});
		String player2Id = "mein2";
		board.addPlayer(new GOGPlayer(player2Id) {

			@Override
			public Move getMove(GOGBoard board) {
				return null;
			}
		});
		System.out.println(board.addPiece(new GOGPiece(player1Id, Type.FLAG), new Vector(0, 0)));
		System.out.println(board.addPiece(new GOGPiece(player2Id, Type.SGT), new Vector(7, 8)));
		board.setCurrentPlayerId(player1Id);
		System.out.println(board);
		board.setCurrentPlayerId(player2Id);
		System.out.println(board);
	}

	public String getCurrentPlayerId() {
		return currentPlayerId;
	}

	public void setCurrentPlayerId(String currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}

}
