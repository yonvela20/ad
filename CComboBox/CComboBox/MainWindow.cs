using System;
using Gtk;
using Serpis.Ad;
using MySql.Data.MySqlClient;

public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();
        App.Instance.Connection = new MySqlConnection("server = localhost; database = dbprueba; user = root; password = sistemas");
        App.Instance.Connection.Open();

        ComboBoxHelper.Fill(comboBox, "SELECT id, nombre from categoria order by nombre", 0);
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
