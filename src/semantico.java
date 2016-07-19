
import bst.BST_math;
import java.util.ArrayList;
import semantico.Variable;
import semantico.ErrorSemantico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author clases
 */
public class semantico extends javax.swing.JFrame {

    /**
     * Creates new form semantico
     */
    String TextSalida = ""; // Deberia ser un Array List con cada uno de los errores
    ArrayList<String> salida = new ArrayList<>();
    Sintatico sintactico;
    
    String contenido;
    ArrayList<Variable> variables = new ArrayList<>();
    ArrayList<Variable> ecuaciones = new ArrayList<>();
    ArrayList<ErrorSemantico> errores = new ArrayList<>();
    
    
    public semantico(String text) {
        this.contenido = text; //El texto lo coloque como variable de la clase
        initComponents();
        //Revisa las correcciones que hice e intenta usar la Clase ErrorSemantico
        //si lo pruebas y funciona bien, por favor elimina los metodos que le coloque
        //el decorador @Deprecated
        CheckStartEnd(text);
        mostrarResultados(); 
        
        
        //Nuevos Metodos
        checkStartEnd();
        loadVariables();
        checkUndefined();
        mostrar();
    }
    
    public void setSintactico(Sintatico sintactico){
        this.sintactico = sintactico;
    }
    
    public void mostrar(){
        if(this.errores.size() == 0){
            Salida.setText("Sin errores semánticos");
            return;
        }
        String salida = "";
        int i = 0;
        for(ErrorSemantico error: this.errores){
            i++;
            salida += i + ".- " +  error.getString() +"\n";
        }
        Salida.setText(salida);
    }
    
    /**
     * Deberia de usar un Array en vez de un String
     * @deprecated
     */
    @Deprecated
    public  void mostrarResultados(){
         if(!"".equals(TextSalida)){
            Salida.setText(TextSalida);
        }
        else{
            Salida.setText("Sin errores semánticos");
        }
          
    }
    
    /**
     * Deberia de usar un Array en vez de un String
     * @deprecated
     */
    @Deprecated
    public void CheckStartEnd(String Conte)
    {
            if(Conte.indexOf("inicio")==-1)
            { TextSalida += "El programa no esta inciado\n";
              
            }
            if(Conte.replaceAll("mmattfin", "").indexOf("fin")==-1)
            { TextSalida += "El programa no esta finalizado\n";
            }
    }
    
    public void checkStartEnd(){
        if(this.contenido.indexOf("inicio")==-1)
        { 
            this.errores.add(new ErrorSemantico(
                    //numero de linea (-1 si no es especificado)
                    -1,
                    //numero de columna (-1 si no es especificado)
                    -1,
                    //Descripcion del error
                    "El programa no esta inciado"));
        }
        if(this.contenido.replaceAll("mmattfin", "").indexOf("fin")==-1)
        { 
            this.errores.add(new ErrorSemantico(
                    //numero de linea (-1 si no es especificado)
                    -1,
                    //numero de columna (-1 si no es especificado)
                    -1,
                    //Descripcion del error
                    "El programa no esta finalizado"));
        }
    }
    
    /**
     * Verifica que las variables que utilice la ecuacion hayan sido declaradas
     */
    public void loadVariables(){
        
        String[] lines = this.contenido.split("\n");
        for(int i = 0; i < lines.length;i++){
            int line_number = i+1;
            //Revisar por variables
            if(lines[i].trim().startsWith("declarar")){
                // Extraer el nombre de la variable
                String variable = lines[i].replace("declarar", "").trim();
                variable = variable.trim();
                //revisar si se proporciono nombre a la declaracion
                if(variable.equals("")){
                    this.errores.add(new ErrorSemantico(
                            line_number, 
                            8, 
                            "Esperado nombre de variable en la declaracion"));
                }else{
                    if(this.ecuaciones.size() > 0 && this.ecuaciones.get(this.ecuaciones.size()-1).line < line_number){
                        this.errores.add(new ErrorSemantico(
                                line_number,
                                0,
                                "Declaraciones de Variables debe establecerse antes de las expresiones matemáticas"
                        ));
                        return;
                    }
                    variables.add(
                        new Variable(
                                variable, 
                                line_number));
                }
                
            }
            if(lines[i].trim().startsWith("mmatini")){
                if(this.variables.size() > 0 && line_number < this.variables.get(this.variables.size()-1).line){
                    this.errores.add(new ErrorSemantico(
                            line_number,
                            lines[i].indexOf("mmatini")+("mmatini").length(),
                            "Expresiones matemática deben esta despues de todas las declaraciones"
                    ));
                    return;
                }
                String ecuacion = "";
                if(lines[i].indexOf("mmattfin") != -1){
                    ecuacion = lines[i].trim().substring(
                            "mmatini".length(),
                            lines[i].indexOf("mmattfin"));
                    ecuacion = ecuacion.replace(" ", "");
                    ecuaciones.add(new Variable(ecuacion, line_number));
                    this.checkUndefined();
                }else{
                    try{
                        boolean end = false;
                        while(!end){
                            String buffer = lines[line_number++].trim();
                            if(buffer.indexOf("mmattfin") != -1){
                                end = true;
                                if(buffer.indexOf("mmattfin") == 0){
                                    ecuacion = ecuacion.replace(" ", "");
                                    ecuaciones.add(new Variable(ecuacion, line_number-1));
                                    this.checkUndefined();
                                    break;
                                }
                                ecuacion += ecuacion.substring(0, ecuacion.indexOf("mmatfin"));
                                ecuacion = ecuacion.replace(" ", "");
                                ecuaciones.add(new Variable(ecuacion, line_number-1));
                            }else{
                                ecuacion += buffer;
                                ecuacion = ecuacion.replace(" ", "");
                                ecuacion = ecuacion.replace("\n\n", "");
                                ecuacion = ecuacion.replace("\n", "");
                            }
                        }
                        
                    }catch(IndexOutOfBoundsException outOfBounds){
                        this.errores.add(new ErrorSemantico(
                            line_number,
                            "mmatini".length(),
                            "Se esperaba mmattfin"    
                        ));
                    }
                    
                }
            }
        }
    }
    
    private boolean isError(ErrorSemantico error){
        for(ErrorSemantico e: this.errores){
            if(e.description.equals(error.description) 
                    && e.line == error.line 
                    && e.col == error.col){
                return true;
            }
        }
        return false;
    }
    
    public void checkUndefined(){
        String[] exclude = "1 2 3 4 5 6 7 8 9 0 * / - + = ( ) [ ] { }".split(" ");
        for(Variable ecuacion: ecuaciones){
            //Esto se hace para separar cada digito y obtener asi las variables
            //dentro de la ecuacion
            BST_math arbolito = new BST_math(ecuacion.getValue().replace(" ",""));
            String[] elem = arbolito.postOrderTraversal().split(" ");
            
            for(String e: elem){
                if(!this.inArray(exclude, e)){
                    // Si entra aqui mas le vale que sea una variable declarada
                    if(!this.inVariables(e)){
                        ErrorSemantico err = new ErrorSemantico(
                        ecuacion.line,
                        ecuacion.getValue().indexOf(e),
                        "Variable: \"" + e + "\" no declarada"
                        );
                        if(!this.isError(err)){
                            errores.add(err);
                        }
                        
                    }
                }
            }
            
        }
        
        //Checkear los mostrar
        String[] lines = this.contenido.split("\n");
        String mostrar_token = "mostrar";
        for(int line_number = 1; line_number <= lines.length;line_number++){
            if(lines[line_number-1].trim().startsWith(mostrar_token)){
                String var = lines[line_number-1].trim().replace(mostrar_token, "");
                var = var.trim();
                if(var.equals("")){
                    ErrorSemantico err = new ErrorSemantico(
                            line_number,
                            mostrar_token.length(),
                            "Se esperaba nombre de Variable"
                    );
                    if(!this.isError(err)){
                        errores.add(err);
                    }
                }
                else if(!this.inVariables(var)){
                    ErrorSemantico err = new ErrorSemantico(
                            line_number,
                            mostrar_token.length(),
                            "Variable: \"" + var + "\" no declarada"
                    );
                    if(!this.isError(err)){
                        errores.add(err);
                    }
                    
                }
            }
        }
    }
    
    private boolean inVariables( String var ){
        for(Variable variable: variables){
            if(variable.getValue().equals(var)){
                return true;
            }
        }
        return false;
    }
    
    private boolean inArray(String[] array, String value){
        for(String v: array){
            if(v.equals(value)){
                return true;
            }
        }
        return false;
    }
    
    
    
    private semantico() {
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

        Resultado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Salida = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        volver = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();

        setResizable(false);

        Resultado.setText("Resulado del Analisis Semantico:");

        Salida.setColumns(20);
        Salida.setLineWrap(true);
        Salida.setRows(5);
        jScrollPane1.setViewportView(Salida);

        jMenu1.setText("Navegar");

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });
        jMenu1.add(volver);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Resultado)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Resultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        setVisible(false);  // TODO add your handling code here:
    }//GEN-LAST:event_volverActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_SalirActionPerformed

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
            java.util.logging.Logger.getLogger(semantico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(semantico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(semantico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(semantico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new semantico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Resultado;
    private javax.swing.JTextArea Salida;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem volver;
    // End of variables declaration//GEN-END:variables
}
