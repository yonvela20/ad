using MySql.Data.MySqlClient;
using System;
using System.Data;


namespace Serpis.Ad
{
    //TODO completar
    class MainClass
    {
		public static void Main(string[] args)
        {
            
            IDbConnection conexion = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
            conexion.Open();

            Categoria categoria = load(1L);
            categoria.Nombre = categoria.Nombre + "#c";
            update(categoria);


        }

        private static Categoria load(object id) {
			
            IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "select * from categoria where id = 1L";
			DbCommandHelper.AddParameter(dbCommand, "id", id);
			IDataReader dataReader = dbCommand.ExecuteReader();

			dataReader.Read(); 

			string nombre = (string)dataReader["nombre"];
			dataReader.Close();

			Categoria categoria = new Categoria();

			categoria.Id = Convert.ToInt64(id);
			categoria.Nombre = nombre;

			return categoria;
        }

        private static void update(Categoria categoria) {
			
            IDbCommand dbCommand = App.Instance.Connection.CreateCommand();
			dbCommand.CommandText = "update categoria set nombre=@nombre where id = @id";
			
            DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
			DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
			
            dbCommand.ExecuteNonQuery();
        }

    }
}
