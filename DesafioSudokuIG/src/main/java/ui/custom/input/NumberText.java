package ui.custom.input;

import model.Posicao;
import service.EventEnum;
import service.EventListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NumberText extends JTextField  implements EventListener {

    private  final Posicao posicao;


    public NumberText(Posicao posicao) {
        this.posicao = posicao;
        Dimension dimension = new Dimension(50,50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!posicao.isFixo());
        if (posicao.isFixo()){
            this.setText(posicao.getValorPreechido().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpace();
            }
            private void changeSpace(){
                if(getText().isEmpty()){
                    posicao.limparValor();
                    return;
                }
                posicao.setValorPreechido(Integer.parseInt(getText()));
            }
        });
    }

    @Override
    public void update(EventEnum eventEnum) {
        if (eventEnum.equals(EventEnum.CLEAR_SPACE)&& (this.isEnabled())){
            this.setText("");
        }
    }
}
