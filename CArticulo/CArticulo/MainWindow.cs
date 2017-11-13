using MySql.Data.MySqlClient;
using System;
using System.Data;
using Gtk;

using Serpis.Ad;
using CArticulo;

public partial class MainWindow : Gtk.Window
{

	public MainWindow() : base(Gtk.WindowType.Toplevel)
	{
		Build();
		Title = "Articulo";
		deleteAction.Sensitive = false;
		editAction.Sensitive = false;

		App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
		App.Instance.Connection.Open();

		TreeViewHelper.Fill(treeView, ArticuloDao.SelectAll);

		treeView.Selection.Changed += delegate {
			bool hasSelected = treeView.Selection.CountSelectedRows() > 0;
			deleteAction.Sensitive = hasSelected;
			editAction.Sensitive = hasSelected;
			//if (treeView.Selection.CountSelectedRows() > 0)
			//    deleteAction.Sensitive = true;
			//else
			//deleteAction.Sensitive = false;
		};

		newAction.Activated += delegate {
			Articulo articulo = new Articulo();
			new ArticuloWindow(articulo);
		};

		editAction.Activated += delegate {
			object id = TreeViewHelper.getId(treeView);
			Articulo articulo = ArticuloDao.Load(id);
			new ArticuloWindow(articulo);
		};

		refreshAction.Activated += delegate {
			TreeViewHelper.Fill(treeView, ArticuloDao.SelectAll);
		};

		deleteAction.Activated += delegate {
			if (WindowsHelper.Confirm(this, "¿Quieres eliminar el registro?"))
			{
				object id = TreeViewHelper.getId(treeView);
				ArticuloDao.Delete(id);
			}
		};
	}

	protected void OnDeleteEvent(object sender, DeleteEventArgs a)
	{
		App.Instance.Connection.Close();

		Application.Quit();
		a.RetVal = true;
	}
}
