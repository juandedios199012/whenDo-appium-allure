package model;

public class NotaBuilder {

    private Nota nota;
    private static NotaBuilder notaBuilder=null;

    private NotaBuilder(){
        nota=new Nota();
    }

    public static NotaBuilder createNota(){
        if (notaBuilder==null)
          notaBuilder=new NotaBuilder();

        return notaBuilder;
    }

    public NotaBuilder titulo(String titulo){
        this.nota.setTitulo(titulo);
        return this;
    }

    public NotaBuilder notas(String notas){
        this.nota.setNotas(notas);
        return this;
    }

    public Nota build(){
        return nota;
    }

}
