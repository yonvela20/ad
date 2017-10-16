﻿using System;
using System.Data;
namespace CCategoria
{
    public class App
    {
        private IDbConnection connection;
        private App()
        {
        }

        private static App instance = new App();
        public static App Instance {
            get { return instance; }
        }

        public IDbConnection Connection{
            get { return connection; }
            set { connection = value; }
        }
    }
}
