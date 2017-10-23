﻿using Gtk;
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

		App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
		App.Instance.Connection.Open();

		treeView.AppendColumn("id", new CellRendererText(), "text", 0);
		treeView.AppendColumn("nombre", new CellRendererText(), "text", 1);
		ListStore listStore = new ListStore(typeof(string), typeof(string));
		treeView.Model = listStore;

		fillListStore(listStore);

		treeView.Selection.Changed += delegate {
			bool hasSelected = treeView.Selection.CountSelectedRows() > 0;
			deleteAction.Sensitive = hasSelected;
			//if (treeView.Selection.CountSelectedRows() > 0)
			//    deleteAction.Sensitive = true;
			//else
			//deleteAction.Sensitive = false;
		};

		newAction.Activated += delegate {
			new CategoriaWindow();
		};

		refreshAction.Activated += delegate {
			fillListStore(listStore);
		};

		deleteAction.Activated += delegate {
			if (WindowsHelper.Confirm(this, "¿Quieres eliminar el registro?"))
			{
				object id = getId();
				IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
				dbCommand.CommandText = "delete from categoria where id = @id";
				DbCommandHelper.AddParameter(dbCommand, "id", id);
				dbCommand.ExecuteNonQuery();
			}


		};
	}

	private object getId()
	{
		TreeIter treeIter;
		treeView.Selection.GetSelected(out treeIter);
		return treeView.Model.GetValue(treeIter, 0);
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
