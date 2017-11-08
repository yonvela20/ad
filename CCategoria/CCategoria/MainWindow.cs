using Gtk;
using MySql.Data.MySqlClient;
using System;
using System.Data;
using Serpis.Ad;

using CCategoria;
public partial class MainWindow : Gtk.Window
{
	public MainWindow() : base(Gtk.WindowType.Toplevel)
	{
		Build();
		Title = "Categoria";
		deleteAction.Sensitive = false;
        editAction.Sensitive = false;

		App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
		App.Instance.Connection.Open();

        TreeViewHelper.Fill(treeView, "select * from categoria");

        //OJO PIOJO
        fillListStore((ListStore)treeView.Model);

		treeView.Selection.Changed += delegate {
			bool hasSelected = treeView.Selection.CountSelectedRows() > 0;
			deleteAction.Sensitive = hasSelected;
            editAction.Sensitive = hasSelected;
		};

		newAction.Activated += delegate {
			new CategoriaWindow();
		};


        editAction.Activated += delegate{
            object id = TreeViewHelper.getId(treeView);
            Categoria categoria = CategoriaDao.Load(id);
            new CategoriaWindow(categoria);
        };

		refreshAction.Activated += delegate {
            //OJO PIOJO
            fillListStore((ListStore)treeView.Model);
		};

		deleteAction.Activated += delegate {
			if (WindowsHelper.Confirm(this, "¿Quieres eliminar el registro?"))
			{
                object id = TreeViewHelper.getId(treeView); 
                CategoriaDao.Delete(id);
			}


		};
	}

	private void fillListStore(ListStore listStore)
	{
		listStore.Clear();
		IDbCommand dbCommnand = App.Instance.Connection.CreateCommand();
		dbCommnand.CommandText = "select * from categoria order by id";
		IDataReader dataReader = dbCommnand.ExecuteReader();
		while (dataReader.Read())
			listStore.AppendValues(dataReader["id"].ToString(), dataReader["nombre"]);
		dataReader.Close();
	}

	protected void OnDeleteEvent(object sender, DeleteEventArgs a)
	{
		App.Instance.Connection.Close();

		Application.Quit();
		a.RetVal = true;
	}
}
