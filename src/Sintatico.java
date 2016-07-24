
import analyzers.LexicalAnalyzer;
import bst.BST_draw;
import bst.BST_math;
import bst.BstMathComponent;
import bst.DisplaySimpleTree;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author clases
 */
public class Sintatico extends javax.swing.JFrame {

    /**
     * Creates new form Sintatico
     */
    ArrayList<String> AMostrar = new ArrayList<>();
    public ArrayList<String> APrograma = new ArrayList<>();
    public ArrayList<String> AVariables = new ArrayList<>();
    String texto;
    public Sintatico(String text) {
        initComponents();
        texto = text;
	SinErrores.setVisible(false);
        Database db = new Database();
        HashMap<String, ? extends Object> db_map = (HashMap) db.getMap();
        LexicalAnalyzer lx = new LexicalAnalyzer(db_map);
        HashMap<String, HashMap> analisis_sintactico = lx.analisys(text);
        
        main.lexicoframe.Analisis(analisis_sintactico, "reserved_words" , Todo , null);
        main.lexicoframe.Analisis(analisis_sintactico, "Simple", Simples, null);
        main.lexicoframe.Analisis(analisis_sintactico, "Compuesta" , Compuesta , null);
        Extraer(text,"declarar",AVariables);
        mostrar(AVariables, Variables);
        Extraer(text,"mostrar",AMostrar);
        Extraer(text,"programa",APrograma);
	Errores(lx.GetError(text));
        mostrar(LexicalAnalyzer.comentario, Comentarios);
        mostrar(LexicalAnalyzer.expresiones, Expresiones);
        if(Expresiones.getItemCount()!=0){
                MostrarArbol.setEnabled(true);
        }

    }
    
    public void mostrar(ArrayList<String> contenido, JComboBox Lista){
        for (String conte  : contenido){
            Lista.addItem(conte);
        }
    };
    
    
    public void Errores(String Conte){
    for (int i=0;i<Variables.getItemCount();i++){
        String error = Variables.getItemAt(i).toString().toLowerCase();
        Conte = Conte.replaceAll(error,"");
    }
    
    for(String mostrar : AMostrar){
        String[] replace = mostrar.split(" ");
        for (int i=0;i<replace.length;i++){
            Conte = Conte.replaceFirst(replace[i].toLowerCase(),"");
        }
    }
    for(String programa : APrograma){
        String[] replace = programa.split(" ");
        for (int i=0;i<replace.length;i++){
        Conte = Conte.replaceFirst(replace[i].toLowerCase(),"");
    }
    }
    
              
    Conte = Conte.replaceAll("\n\n", "");
    String[] Error = Conte.trim().split("\n");
    if (Error.length>0){
        boolean hayError = false;
	for (String error : Error){
	    if(!"".equals(error)){
                Errores.addItem(error);
                hayError = true;
            }
	    
	}
        if(hayError){
           Semantico.setEnabled(false);
           main.semantico.setEnabled(false); 
           return;
        }
            
    }
	
        Errores.setVisible(false);
        SinErrores.setVisible(true);
        Semantico.setEnabled(true);
        main.semantico.setEnabled(true);
	
    }
    
    public void Extraer(String Conte, String palabra, ArrayList<String> Array){
        String[] variables = Conte.split("\n");
        for (String variable : variables){
            if(variable.trim().startsWith(palabra)){
                variable = variable.replaceAll(palabra, "");
                Array.add(variable.trim());
            }
        }
        
    }
    
    private Sintatico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EtiquetaArbol = new javax.swing.JLabel();
        PanelTodo = new javax.swing.JPanel();
        EtiquetaTodo = new javax.swing.JLabel();
        Todo = new javax.swing.JComboBox();
        PanelSimples = new javax.swing.JPanel();
        EtiquetaSimples = new javax.swing.JLabel();
        Simples = new javax.swing.JComboBox();
        PanelCompuesta = new javax.swing.JPanel();
        EtiquetaCompuesta = new javax.swing.JLabel();
        Compuesta = new javax.swing.JComboBox();
        PanelVariables = new javax.swing.JPanel();
        EtiquetaVariables = new javax.swing.JLabel();
        Variables = new javax.swing.JComboBox();
        PanelExpresiones = new javax.swing.JPanel();
        EtiquetaExpresiones = new javax.swing.JLabel();
        Expresiones = new javax.swing.JComboBox();
        PanelComentarios = new javax.swing.JPanel();
        EtiquetaComentarios = new javax.swing.JLabel();
        Comentarios = new javax.swing.JComboBox();
        PanelErrores = new javax.swing.JPanel();
        EtiquetaErrores = new javax.swing.JLabel();
        Errores = new javax.swing.JComboBox();
        SinErrores = new javax.swing.JLabel();
        ecuacionSeleccionada = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        MostrarArbol = new javax.swing.JButton();
        postorden_label = new javax.swing.JLabel();
        preorder_textfield = new javax.swing.JTextField();
        postorder_textfield = new javax.swing.JTextField();
        inorder_textfield = new javax.swing.JTextField();
        inorder_label = new javax.swing.JLabel();
        preorder_label = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        volver = new javax.swing.JMenuItem();
        Semantico = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();

        setTitle("Analisis Sintactico");
        setResizable(false);

        EtiquetaArbol.setText("Arbol:");

        PanelTodo.setBackground(new java.awt.Color(204, 204, 204));

        EtiquetaTodo.setText("Todas las Palabras:");

        javax.swing.GroupLayout PanelTodoLayout = new javax.swing.GroupLayout(PanelTodo);
        PanelTodo.setLayout(PanelTodoLayout);
        PanelTodoLayout.setHorizontalGroup(
            PanelTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTodoLayout.createSequentialGroup()
                        .addComponent(EtiquetaTodo)
                        .addGap(0, 166, Short.MAX_VALUE))
                    .addComponent(Todo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelTodoLayout.setVerticalGroup(
            PanelTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTodoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EtiquetaTodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Todo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelSimples.setBackground(new java.awt.Color(204, 204, 204));

        EtiquetaSimples.setText("Palabras Simples:");

        javax.swing.GroupLayout PanelSimplesLayout = new javax.swing.GroupLayout(PanelSimples);
        PanelSimples.setLayout(PanelSimplesLayout);
        PanelSimplesLayout.setHorizontalGroup(
            PanelSimplesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSimplesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSimplesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSimplesLayout.createSequentialGroup()
                        .addComponent(EtiquetaSimples)
                        .addGap(0, 176, Short.MAX_VALUE))
                    .addComponent(Simples, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelSimplesLayout.setVerticalGroup(
            PanelSimplesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSimplesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EtiquetaSimples)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Simples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelCompuesta.setBackground(new java.awt.Color(204, 204, 204));

        EtiquetaCompuesta.setText("Palabras Compuesta:");

        javax.swing.GroupLayout PanelCompuestaLayout = new javax.swing.GroupLayout(PanelCompuesta);
        PanelCompuesta.setLayout(PanelCompuestaLayout);
        PanelCompuestaLayout.setHorizontalGroup(
            PanelCompuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCompuestaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCompuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCompuestaLayout.createSequentialGroup()
                        .addComponent(EtiquetaCompuesta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Compuesta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelCompuestaLayout.setVerticalGroup(
            PanelCompuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCompuestaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EtiquetaCompuesta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Compuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelVariables.setBackground(new java.awt.Color(204, 204, 204));

        EtiquetaVariables.setText("Variables Declaradas:");

        javax.swing.GroupLayout PanelVariablesLayout = new javax.swing.GroupLayout(PanelVariables);
        PanelVariables.setLayout(PanelVariablesLayout);
        PanelVariablesLayout.setHorizontalGroup(
            PanelVariablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVariablesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVariablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVariablesLayout.createSequentialGroup()
                        .addComponent(EtiquetaVariables)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Variables, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelVariablesLayout.setVerticalGroup(
            PanelVariablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVariablesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EtiquetaVariables)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Variables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelExpresiones.setBackground(new java.awt.Color(204, 204, 204));

        EtiquetaExpresiones.setText("Expresiones Matematicas:");

        Expresiones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ExpresionesItemStateChanged(evt);
            }
        });
        Expresiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpresionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelExpresionesLayout = new javax.swing.GroupLayout(PanelExpresiones);
        PanelExpresiones.setLayout(PanelExpresionesLayout);
        PanelExpresionesLayout.setHorizontalGroup(
            PanelExpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExpresionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelExpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelExpresionesLayout.createSequentialGroup()
                        .addComponent(EtiquetaExpresiones)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Expresiones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelExpresionesLayout.setVerticalGroup(
            PanelExpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelExpresionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EtiquetaExpresiones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Expresiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelComentarios.setBackground(new java.awt.Color(204, 204, 204));

        EtiquetaComentarios.setText("Comentarios:");

        javax.swing.GroupLayout PanelComentariosLayout = new javax.swing.GroupLayout(PanelComentarios);
        PanelComentarios.setLayout(PanelComentariosLayout);
        PanelComentariosLayout.setHorizontalGroup(
            PanelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelComentariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelComentariosLayout.createSequentialGroup()
                        .addComponent(EtiquetaComentarios)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Comentarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelComentariosLayout.setVerticalGroup(
            PanelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelComentariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EtiquetaComentarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Comentarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelErrores.setBackground(new java.awt.Color(204, 204, 204));

        EtiquetaErrores.setText("Errores Sintactico:");

        SinErrores.setText("No Hay Errores");

        javax.swing.GroupLayout PanelErroresLayout = new javax.swing.GroupLayout(PanelErrores);
        PanelErrores.setLayout(PanelErroresLayout);
        PanelErroresLayout.setHorizontalGroup(
            PanelErroresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelErroresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelErroresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelErroresLayout.createSequentialGroup()
                        .addComponent(EtiquetaErrores)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelErroresLayout.createSequentialGroup()
                        .addComponent(SinErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Errores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelErroresLayout.setVerticalGroup(
            PanelErroresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelErroresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EtiquetaErrores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelErroresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Errores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SinErrores))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ecuacionSeleccionada.setEditable(false);
        ecuacionSeleccionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ecuacionSeleccionadaActionPerformed(evt);
            }
        });

        jLabel1.setText("Ecuacion Seleccionada");

        MostrarArbol.setText("Mostrar Arbol");
        MostrarArbol.setEnabled(false);
        MostrarArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarArbolActionPerformed(evt);
            }
        });

        postorden_label.setText("Postorden");

        postorder_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postorder_textfieldActionPerformed(evt);
            }
        });

        inorder_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inorder_textfieldActionPerformed(evt);
            }
        });

        inorder_label.setText("Inorder");

        preorder_label.setText("Preorder");

        jMenu1.setText("Navegar");

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        jMenu1.add(volver);

        Semantico.setText("Semantico");
        Semantico.setEnabled(false);
        Semantico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SemanticoActionPerformed(evt);
            }
        });
        jMenu1.add(Semantico);

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PanelTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelCompuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelVariables, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelExpresiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelComentarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PanelSimples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ecuacionSeleccionada)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(postorder_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inorder_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(preorder_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EtiquetaArbol))
                                .addGap(0, 12, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(preorder_label))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(inorder_label))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(postorden_label))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(MostrarArbol)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelSimples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelCompuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelVariables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EtiquetaArbol)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ecuacionSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MostrarArbol)
                        .addGap(18, 18, 18)
                        .addComponent(postorden_label)
                        .addGap(18, 18, 18)
                        .addComponent(postorder_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inorder_label)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelExpresiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelErrores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inorder_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(preorder_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(preorder_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
            System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_SalirActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
            setVisible(false);  // TODO add your handling code here:
    }//GEN-LAST:event_volverActionPerformed

    private void ExpresionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ExpresionesItemStateChanged
        if(evt.getStateChange() == 1){
            String ecuacion_string = (String) evt.getItem();
            ecuacionSeleccionada.setText(ecuacion_string);
            BST_math arbolito = new BST_math(ecuacion_string);
            preorder_textfield.setText(arbolito.preOrderTraversal());
            inorder_textfield.setText(arbolito.inOrderTraversal());
            postorder_textfield.setText(arbolito.postOrderTraversal());
        }
    }//GEN-LAST:event_ExpresionesItemStateChanged

    private void ecuacionSeleccionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ecuacionSeleccionadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ecuacionSeleccionadaActionPerformed

    private void MostrarArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarArbolActionPerformed
        String ecuacion_string = ecuacionSeleccionada.getText();
        BstMathComponent ecuacion = new BstMathComponent(ecuacion_string);
        BST_draw ventana_arbol = new BST_draw(ecuacion);
        ventana_arbol.setVisible(true);
    }//GEN-LAST:event_MostrarArbolActionPerformed

    private void postorder_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postorder_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_postorder_textfieldActionPerformed

    private void inorder_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inorder_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inorder_textfieldActionPerformed

    private void ExpresionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExpresionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExpresionesActionPerformed

    private void SemanticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SemanticoActionPerformed
        new semantico(texto).setVisible(true);
        setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_SemanticoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sintatico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sintatico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sintatico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sintatico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sintatico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Comentarios;
    private javax.swing.JComboBox Compuesta;
    private javax.swing.JComboBox Errores;
    private javax.swing.JLabel EtiquetaArbol;
    private javax.swing.JLabel EtiquetaComentarios;
    private javax.swing.JLabel EtiquetaCompuesta;
    private javax.swing.JLabel EtiquetaErrores;
    private javax.swing.JLabel EtiquetaExpresiones;
    private javax.swing.JLabel EtiquetaSimples;
    private javax.swing.JLabel EtiquetaTodo;
    private javax.swing.JLabel EtiquetaVariables;
    private javax.swing.JComboBox Expresiones;
    private javax.swing.JButton MostrarArbol;
    private javax.swing.JPanel PanelComentarios;
    private javax.swing.JPanel PanelCompuesta;
    private javax.swing.JPanel PanelErrores;
    private javax.swing.JPanel PanelExpresiones;
    private javax.swing.JPanel PanelSimples;
    private javax.swing.JPanel PanelTodo;
    private javax.swing.JPanel PanelVariables;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuItem Semantico;
    private javax.swing.JComboBox Simples;
    private javax.swing.JLabel SinErrores;
    private javax.swing.JComboBox Todo;
    private javax.swing.JComboBox Variables;
    private javax.swing.JTextField ecuacionSeleccionada;
    private javax.swing.JLabel inorder_label;
    private javax.swing.JTextField inorder_textfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel postorden_label;
    private javax.swing.JTextField postorder_textfield;
    private javax.swing.JLabel preorder_label;
    private javax.swing.JTextField preorder_textfield;
    private javax.swing.JMenuItem volver;
    // End of variables declaration//GEN-END:variables
}
