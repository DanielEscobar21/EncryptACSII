import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame{
    private final BorderLayout layout;
    private final JTextField txtFile; 
    private final JTextArea text;
    private final JButton encryptJButton;
    private final JButton decryptJButton;
    
    Ventana(){
    	super("Testing cipher");
        layout = new BorderLayout(20, 10);
        setLayout(layout);
        JPanel fileJPanel = new JPanel();
        JLabel lblFile = new JLabel( "Archivo de texto/cifrado:" );
        fileJPanel.add(lblFile);
        txtFile = new JTextField(20);
        fileJPanel.add(txtFile);
        add(fileJPanel, BorderLayout.NORTH);
        text = new JTextArea(10,15);
    	add(text, BorderLayout.CENTER);
        
        JPanel buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new BorderLayout());
        encryptJButton = new JButton("Encriptar");
    	buttonJPanel.add(encryptJButton, BorderLayout.NORTH);
        add(buttonJPanel, BorderLayout.EAST);
        
        txtFile.addActionListener(new txtAction()); 
        btnEncriptar e = new btnEncriptar();
        encryptJButton.addActionListener(e);
        }
    
    class txtAction implements ActionListener{
    	@Override
          public void actionPerformed(ActionEvent event)
          {
              text.setText(event.getActionCommand());
          }
          
    }
    class btnEncriptar implements ActionListener{
        @Override
            public void actionPerformed(ActionEvent e){
                String encript = text.getText();
                String textoEncriptado = encriptar(encript);
                text.setText(textoEncriptado);      
            }
    }
    
    
    
    String encriptar(String s){
        char[] convert = new char[40];
        char[] lugar = new char[40];
        StringBuffer ec = new StringBuffer();
        String codigo= text.getText();
        String palabra = codigo.trim();
        float aux = palabra.length();
        
        for (int i = 0; i < aux; i++) {
            char caracter = palabra.charAt(i);
            int ascii = (int)caracter+3;
            char codi = (char)ascii;
            convert[i] = codi;
        }
        
        for (int i = 1; i <= Math.round((aux/2)-.1); i++) {
            int num = (int)(aux)-i;
            lugar[i] = convert[num];
        }
        
        for (int i = 1; i <= Math.round((aux/2)); i++) {
            int auxChar = Math.round((aux/2)) - i;
            
            char caracter = convert[auxChar];
            int ascii = (int)caracter-1;
            char codi = (char)ascii;
            int aux1 = (int) Math.round((aux/2)-.1)+i;
            lugar[aux1] = codi;
        }
        
        for (int i = 0; i <= aux; i++) {
            ec = ec.append(lugar[i]);
        }
        return  "Encriptación:  "+ ec;
    }
    
    
   public static void main(String[] args)
   { 
      Ventana ventana = new Ventana(); 
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setResizable(false);
      ventana.setSize(500, 200); 
      ventana.setVisible(true);
   } 
}
