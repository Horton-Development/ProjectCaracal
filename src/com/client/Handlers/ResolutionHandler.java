package com.client.Handlers;

public enum ResolutionHandler{

	// Small dimension
	SMALL{
		public int getWidth(){
			return 640;
		}

		public int getHeight(){
			return 480;
		}
	},

	// Medium dimension
	MEDIUM{
		public int getWidth(){
			return 800;
		}

		public int getHeight(){
			return 600;
		}
	},

	// Large dimension
	LARGE{
		public int getWidth(){
			return 1280;
		}

		public int getHeight(){
			return 960;
		}
	};

	public abstract int getHeight();

	public abstract int getWidth();

}
