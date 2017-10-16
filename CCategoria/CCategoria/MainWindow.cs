using Gtk;
using MySql.Data.MySqlClient;
using System;
using System.Data;
using CCategoria;

public partial class MainWindow : Gtk.Window
{

    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

        String conex = "server=localhost; database=dbprueba; user=root; password=sistemas";
        App.Instance.Connection = new MySqlConnection(conex);
        App.Instance.Connection.Open();

        //declara columnas 
		treeView.AppendColumn("id", new CellRendererText(), "text", 0);
		treeView.AppendColumn("nombre", new CellRendererText(), "text", 1);
        ListStore listStore = new ListStore(typeof(ulong), typeof(string));

        //rellena las columnas leyendo la base de datos
        fillListStore(listStore);
        //Nueva accion
        newAction.Activated += delegate {
            new CategoriaWindow();
        };

        //Refrescar 
        refreshAction.Activated += delegate {
            listStore.Clear();
            fillListStore(listStore);
		};

        treeView.Model = listStore;
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        App.Instance.Connection.Close();

        Application.Quit();
        a.RetVal = true;
    }

    private void fillListStore(ListStore listStore){
		IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
		dbCommand.CommandText = "select * from categoria";
		IDataReader dataReader = dbCommand.ExecuteReader();
		while (dataReader.Read())
		listStore.AppendValues(dataReader["id"], dataReader["nombre"]);
        dataReader.Close();
    }
}
