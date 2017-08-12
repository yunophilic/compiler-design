package x86;

	public class Quad {

		Symbol label;
		String op;
		Symbol src1;
		Symbol src2;
		Symbol dst;


		public Quad (SymStack s, int l, Symbol d, Symbol s1, Symbol s2, String o) {
			label = s.Add(l);
			dst = d;
			src1 = s1;
			src2 = s2;
			op = o;
		}

		public Quad (Symbol l) {
			label = l;
			dst = null;
			src1 = null;
			src2 = null;
			op = "";
		}

		public void BackPatch (Symbol l) {
			dst = l;
		}

		public Symbol GetLabel () {
			return label;
		}

		public void Print () {
			System.out.print(label.GetName() + ": ");
			if (dst != null) System.out.print(dst.GetName());
			if (src1 != null) System.out.print(" = " + src1.GetName());
			System.out.print(" " + op + " ");
			if (src2 != null) System.out.print(src2.GetName());
			System.out.println("");
		}

		public void AsmPrint () {
			
			System.out.print(label.GetName() + ": ");


			if (op.equals("")) { //label
				System.out.println("push %rbp");
				System.out.println("mov %rsp, %rbp");
			} else if (op.equals("frame")) {
				System.out.println("sub " + dst.AsmPrint() + ", %rsp");
			} else if (op.equals("ret")) { //return
				if (src1 != null) System.out.println("mov -" + src1.GetOffset() + "(%rbp), %rax");
				System.out.println("add $" + dst.GetName() + ", %rsp");
				System.out.println("pop %rbp");
				System.out.println("ret");
			} else if (op.equals("=")) { //assignment
				ReadSrc1(src1);
				WriteDst(dst);

			} else if (op.equals("+")) { //addition
				ReadSrc1(src1);
				ReadSrc2(src2);
				Compute("add");
				WriteDst(dst);

			}  else if (op.equals("-")) {
				ReadSrc1(src1);
				ReadSrc2(src2);
				Compute("sub");
				WriteDst(dst);

			} else if (op.equals("*")) {
				ReadSrc1(src1);
				ReadSrc2(src2);
				Compute("mulq");
				WriteDst(dst);

			} else if (op.equals("/")) {
				System.out.println("mov $0, %rdx");
				ReadSrc1(src1);
				ReadSrc2(src2);
				Compute("idiv");
				WriteDst(dst);

			} else if (op.equals("[]")) {
				ReadSrc1(src1);
				ReadSrc2(src2);
				Compute("add");
				System.out.println("mov (%rax), %rbx");
				System.out.println("mov %rbx, -" + dst.GetOffset() + "(%rbp)");

			} else if (op.equals("[]=")) {
				ReadSrc1(dst);
				ReadSrc2(src1);
				Compute("add");
				ReadSrc2(src2);
				System.out.println("mov %rbx, (%rax)");

			} else if (op.equals("cmp")) {
				ReadSrc1(src1);
				ReadSrc2(src2);
				Compute("cmp");
				WriteDst(dst);

			} else if (
				op.equals("jl") ||
				op.equals("jle") ||
				op.equals("jg") ||
				op.equals("jge") ||
				op.equals("je") ||
				op.equals("jne")
			) {
				System.out.println(op + " " + dst.GetName());

			} else if (op.equals("goto")) {
				System.out.println("jmp " + dst.GetName());

			} else if (
				op.equals("rdi") ||
				op.equals("rsi") ||
				op.equals("rdx") ||
				op.equals("rcx") ||
				op.equals("r8") ||
				op.equals("r9")
			) {
				System.out.println("mov " + dst.AsmPrint() + ", %" + op);
				
			} else if (op.equals("call")) {
				System.out.println("call " + src1.GetName());

			} else if (op.equals("callexp")) {
				System.out.println("call " + src1.GetName());
				System.out.println("mov %rax, -" + dst.GetOffset() + "(%rbp)");

			} else if (op.equals("push %rdi")) {
				System.out.println("mov %rdi, -16(%rbp)");

			} else if (op.equals("push %rsi")) {
				System.out.println("mov %rsi, -16(%rbp)");

			} else if (op.equals("push %rdx")) {
				System.out.println("mov %rdx, -16(%rbp)");

			} else if (op.equals("push %rcx")) {
				System.out.println("mov %rcx, -16(%rbp)");

			} else if (op.equals("push %r8")) {
				System.out.println("mov %r8, -16(%rbp)");

			} else if (op.equals("push %r9")) {
				System.out.println("mov %r9, -16(%rbp)");

			}
		}

		void Compute (String opcode) {
			switch (opcode) {
				case "mulq":
				case "idiv":
					System.out.println(opcode + " %rbx");
					break;
				case "cmp":
					System.out.println(opcode + " %rax, %rbx");
					break;
				default:
					System.out.println(opcode + " %rbx, %rax");
					break;
			}
		}

		void ReadSrc1 (Symbol src) {
			System.out.println("mov " + src.AsmPrint() + ", %rax");
		}

		void ReadSrc2 (Symbol src) {
			System.out.println("mov " + src.AsmPrint() + ", %rbx");
		}

		void WriteDst (Symbol dst) {
			System.out.println("mov %rax, " + dst.AsmPrint());
		}



	}