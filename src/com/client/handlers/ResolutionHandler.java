package com.client.handlers;

public enum ResolutionHandler{

	// Small dimension
	SMALL{
		@Override
		public int getWidth(){
			return 640;
		}

		@Override
		public int getHeight(){
			return 480;
		}
	},

	// Medium dimension
	MEDIUM{
		@Override
		public int getWidth(){
			return 800;
		}

		@Override
		public int getHeight(){
			return 600;
		}
	},

	// Large dimension
	LARGE{
		@Override
		public int getWidth(){
			return 1280;
		}

		@Override
		public int getHeight(){
			return 960;
		}
	};

	public abstract int getHeight();

	public abstract int getWidth();

}
