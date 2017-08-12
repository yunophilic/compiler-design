package x86;

public class QuadTab {

	Quad qt[];
	int size;
	SymStack s;

	public QuadTab (SymStack stk) {
		qt = new Quad[1000];
		size = 0;
		s = stk;
	}



	public int Add(Symbol dst, Symbol src1, Symbol src2, String op) {
		
		qt[size] = new Quad(s, size, dst, src1, src2, op);
		return (size ++);
	}

	public int Add(Symbol label) { 
		qt[size] = new Quad (label);
		return (size ++);
	}

	public int GetNextLabel () {
		return size;
	}

	public void BackPatch (int id, Symbol label) {
		qt[id].BackPatch(label);
	}

	public void Print() {
		for (int  i = 0; i < size; i ++) {
			qt[i].Print();
		}
	}

	public void AsmPrint() {
		for (int  i = 0; i < size; i ++) {
			System.out.println();
			System.out.print("#");
			qt[i].Print();
			qt[i].AsmPrint();
			System.out.println();
		}
	}
}