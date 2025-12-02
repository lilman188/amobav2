package amobav;

/**
 * Egy lépést reprezentál a játékban.
 * Egy lépés sor és oszlop koordinátát tartalmaz.
 *
 * @param row a lépés sor indexe (0-tól)
 * @param col a lépés oszlop indexe (0-tól)
 */
public record Move(int row, int col) { }
