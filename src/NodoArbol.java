import java.util.ArrayList;

public class NodoArbol {
  String tipo;
  ArrayList<NodoArbol> hijos;
  String lexema;

  public NodoArbol() {
    this.tipo = "";
    this.hijos = new ArrayList<NodoArbol>();
    this.lexema = "";
  }
  public NodoArbol(String lexema) {
    this.tipo = "";
    this.hijos = new ArrayList<NodoArbol>();
    this.lexema = lexema;
  }
  public NodoArbol(String tipo, String lexema) {
    this.tipo = tipo;
    this.lexema = lexema;
    this.hijos = new ArrayList<NodoArbol>();
  }
  public void agregarHijo(NodoArbol hijo) {
    this.hijos.add(hijo);
  }
  public String getTipo() {
    return this.tipo;
  }
  public ArrayList<NodoArbol> getHijos() {
    return this.hijos;
  }
  public String getLexema() {
    return this.lexema;
  }
  public void setLexema(String lexema) {
    this.lexema = lexema;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }


  public void printArbol() {
    if (this.hijos.size() == 0) {
      System.out.print(this.tipo + " ");
    } else {
      System.out.print("(" + this.tipo + " ");
      for (NodoArbol hijo : this.hijos) {
        hijo.printArbol();
      }
      System.out.print(") ");
    }
  }

}