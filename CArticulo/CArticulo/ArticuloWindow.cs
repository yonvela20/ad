using System;
using System.Data;
using Serpis.Ad;

namespace CArticulo
{
	public partial class ArticuloWindow : Gtk.Window
	{
		public ArticuloWindow(Articulo articulo) : base(Gtk.WindowType.Toplevel)
		{
			this.Build();
            entryNombre.Text = articulo.Nombre; 
            spinButtonPrecio.Value = (double)articulo.Precio;
            entryCategoria.Text = articulo.Categoria.ToString();

			saveAction.Activated += delegate {
				articulo.Nombre = entryNombre.Text;
                articulo.Precio = (decimal)spinButtonPrecio.Value;
                articulo.Categoria = long.Parse(entryCategoria.Text);
				ArticuloDao.Save(articulo);
				Destroy();
			};
		}
	}
}

