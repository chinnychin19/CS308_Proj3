// package view.sidebar;
//
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;
// import java.util.Map;
// import javax.swing.JLabel;
// import view.input.Textbox;
// import model.Model;
//
//
// @SuppressWarnings("serial")
// public class VariableModule extends Module {
//
// public VariableModule (int width, int height,Textbox textbox) {
// super(width, height,textbox);
// this.add(new JLabel("Variable Module"));
// // TODO Auto-generated constructor stub
// }
//
// public VariableModule () {
// super();
// this.add(new JLabel("Variable Module"));
// }
//
//
//
//
// @Override
// protected void updateContent () {
// }
//
// @Override
// protected Collection<ModuleData> getStoredModelInformation () {
// Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
// Map<String,String> variableMap = Model.getAllVariables ();
// for (String key: variableMap.keySet()){
// variableCollection.add(new ModuleData(key +":" + variableMap.get(key) ,variableMap.get(key)));
// }
// return variableCollection;
// }
//
// }
