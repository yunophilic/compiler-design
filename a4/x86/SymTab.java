package x86;


	public class SymTab {
	
		Symbol st[];
		int size;
		
				

		public SymTab (int o) {
			st = new Symbol[1000];
			size = 0;
			//offset = o;
			
		}

		public Symbol Find (String n) {
			for (int  i = 0; i < size; i ++) {
				if (st[i].Equal(n)) return st[i];
			}
		
			return null;
		}

		public Symbol insert(String n, DataType d, int offset) {
			Symbol id = Find(n);
			if (id != null) return id;
	
			return(Add(n, d, offset));

		}

		public Symbol insert(String n, DataType d, Boolean isConstant, int offset) {
			Symbol id = Find(n);
			if (id != null) return id;
	
			return(Add(n, d, isConstant, offset));

		}



		public Symbol Add (String n, DataType d, int offset) {
			st[size] = new Symbol(n, d, offset);
			size ++;
			//IncOffset();
			return (st[size - 1]);
		}

		public Symbol Add (String n, DataType d, Symbol val, int offset) {
			st[size] = new Symbol(n, d, val, offset);
			size ++;
			//IncOffset();
			return (st[size - 1]);
		}


		public Symbol Add (String n, DataType d, Boolean isConstant, int offset) {
			st[size] = new Symbol(n, d, isConstant, offset);
			size ++;
			//IncOffset();
			return (st[size - 1]);
		}

		public Symbol Add (String n, DataType d, int arrSz, int offset) {
			st[size] = new Symbol(n, d, arrSz, offset);
			size ++;
			//IncOffset();
			return (st[size - 1]);
		}

		public Symbol Add (int id, DataType d, int offset) {
			st [size] = new Symbol (id, d, offset);
			size ++;
			//IncOffset();
			return (st[size - 1]);
		}

		public Symbol Add (int id, DataType d, Boolean isConstant, int offset) {
			st [size] = new Symbol (id, d, isConstant, offset);
			size ++;
			//IncOffset();
			return (st[size - 1]);
		}

		public DataType GetType (int id) {
			if (id == -1) return DataType.INVALID;
			return (st[id].GetType());
		}

		public String GetName (int id) {
			if (id == -1) return ("");
			return (st[id].GetName()); 
		}

		public void Print() {
			for (int  i = 0; i < size; i ++) {
				st[i].Print();
			}
		}

		public void PrintGlobals() {
			for (int i = 0; i < size; i++) {

				if (!((st[i].GetType() == DataType.INT) || (st[i].GetType() == DataType.BOOLEAN) || (st[i].GetType() == DataType.STR))) continue;
				if (st[i].isConstant() == Boolean.TRUE) continue;

				

				if (st[i].GetType() == DataType.STR) {
					System.out.println("str" + st[i].GetOffset() + ": .asciz " + st[i].GetName());
				} else if (st[i].GetSize() == 0) { //scalar global
					st[i].isGlobal = Boolean.TRUE;
					System.out.println(st[i].GetName() + ": .quad " + st[i].GetInitVal());
				} else { //array global
					st[i].isGlobal = Boolean.TRUE;
					int nElem = st[i].GetSize();
					System.out.print(st[i].GetName() + ": .quad 0");
					for (int j = 0; j < nElem - 1; j++) {
						System.out.print(", 0");
					}
					System.out.println();
				} //array global
			}//each global
		}

	

	}
