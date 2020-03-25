package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JBrainTetris extends JTetris {
    private JSlider adversary;
    private JLabel status;
    private DefaultBrain db;
    private JCheckBox brainMode;

    private Brain.Move move;


    public JBrainTetris(int pixels){
        super(pixels);
        db = new DefaultBrain();

    }

    @Override
    public JComponent createControlPanel(){
        JPanel panel = (JPanel) super.createControlPanel();
        panel.add(new JLabel("Brain:"));
        brainMode = new JCheckBox("Brain active");
        panel.add(brainMode);

        JPanel little = new JPanel();
        little.add(new JLabel("Adversary:"));
        adversary = new JSlider(0, 100, 0); // min, max, current
        adversary.setPreferredSize(new Dimension(100,15));

        status = new JLabel("ok");
        little.add(adversary);
        little.add(status);
        panel.add(little);
        return panel;
    }

    @Override
    public Piece pickNextPiece() {
        int rand = (new Random()).nextInt(99);
        if(rand >= adversary.getValue()){
            Piece pc = super.pickNextPiece();
            status.setText("ok");
            move = db.bestMove(board, pc, board.getHeight(), null);
            return pc;
        }
        status.setText("*ok*");
        Piece[] pieces = Piece.getPieces();
        Piece worstPiece = pieces[0];
        double worstScore = 0.0;
        for(Piece p: pieces){
            board.undo();
            Brain.Move curMove = db.bestMove(board, p, board.getHeight(), null);
            if(curMove != null & curMove.score > worstScore){
                worstPiece = p;
                move = curMove;
            }
        }
        return worstPiece;
    }

    @Override
    public void tick(int verb){
        if(!brainMode.isSelected() || verb != DOWN){
            super.tick(verb);
            return;
        }

        if(move != null) {
            if (!currentPiece.equals(move.piece)) super.tick(ROTATE);
            if (move.x < currentX) {
                super.tick(LEFT);
            } else if (move.x > currentX) super.tick(RIGHT);
        }
        super.tick(verb);
    }

    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        JBrainTetris jbt = new JBrainTetris(16);
        JFrame frame = jbt.createFrame(jbt);
        frame.setVisible(true);
    }
}
