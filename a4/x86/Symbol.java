package x86;

	public class Symbol {
	
		String name;
		DataType dt;
		int arrSize;
		Boolean isConst;
		Boolean inited;
		Symbol initVal;
		int offset;
		Boolean isGlobal;





		public Symbol (String n, DataType d, int o) {
			name = n;
			dt = d;
			arrSize = 0;
			isConst = Boolean.FALSE;
			inited = Boolean.FALSE;
			offset = o;
			isGlobal = Boolean.FALSE;
		}

		public Symbol (String n, DataType d, Symbol val, int o) {
			name = n;
			dt = d;
			arrSize = 0;
			isConst = Boolean.FALSE;
			inited = Boolean.TRUE;
			initVal = val;
			offset = o;
			isGlobal = Boolean.FALSE;
		}


		public Symbol (String n, DataType d, int arrSz, int o) {
			name = n;
			dt = d;
			arrSize = arrSz;
			isConst = Boolean.FALSE;
			inited = Boolean.FALSE;
			offset = o;
			isGlobal = Boolean.FALSE;
		}

		public Symbol (String n, DataType d, Boolean isConstant, int o) {
			name = n;
			dt = d;
			arrSize = 0;
			isConst = isConstant;
			inited = Boolean.FALSE;
			offset = o;
			isGlobal = Boolean.FALSE;
		}

		public Symbol (int id, DataType d, int o) {
			if (d == DataType.LABEL) name = "L_" + id;
			else name = "t_" + id;
			dt = d;
			arrSize = 0;
			isConst = Boolean.FALSE;
			inited = Boolean.FALSE;
			offset = o;
			isGlobal = Boolean.FALSE;
		}


		public Symbol (int id, DataType d, Boolean isConstant, int o) {
			if (d == DataType.LABEL) name = "L_" + id;
			else name = "t_" + id;
			dt = d;
			arrSize = 0;
			isConst = isConstant;
			inited = Boolean.FALSE;
			offset = o;
			isGlobal = Boolean.FALSE;
		}

		

		public boolean Equal (String n) {
			return (name.equals(n));
		}

		public DataType GetType () {
			return dt;
		}

		public int GetSize () {
			return arrSize;
		}

		public String GetName () {
			return name;
		}

		public String GetInitVal () {
			if (inited == Boolean.TRUE) {
				if (dt == DataType.INT) return(initVal.GetName());
				else if (initVal.GetName().equals("true")) return ("1");
				else return("0");
			}
			return ("0");
		}

		public Boolean isConstant () {
			return isConst;
		}

		public void Print() {
			System.out.println("# " + name + "\t\t" + dt + "\t" + arrSize + "\t" + offset);
		}

		public int GetOffset () {
			return offset;
		}

		public String AsmPrint () {
			if (isConst || isGlobal) {
				if ((dt == DataType.LABEL) || (isConst) || (arrSize != 0)) return ("$" + GetName());
				else return (GetName());
			}
			else if (dt == DataType.STR) return ("$str" + GetOffset());
			else if ((dt == DataType.INT) || (dt == DataType.BOOLEAN)) return ("-" + GetOffset() + "(%rbp)");
			else return GetName();
		}



	
	
	}