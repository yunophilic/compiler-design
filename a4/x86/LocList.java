package x86;

	public class LocList {
		int[] locs;
		int size;
		

		public LocList () {
			locs = new int [1000];
			size = 0;
		}

		public void Add (int l) {
			locs[size] = l;
			size ++;
		}

		public void BackPatch (QuadTab q, Symbol label) {
			for (int i = 0; i < size; i ++) {
				q.BackPatch(locs[i], label);
			}
		
		}

		public void Merge (LocList ll) {
			for (int i = 0; i < ll.size; i++) {
				locs[size] = ll.locs[i];
				size ++;
			}
		}

		public boolean IsEmpty () {
			return (size == 0);
		}

		public void Print () {
			for (int i = 0; i < size; i ++) {
				System.out.print(locs[i] + " ");
			}
			System.out.println("");
		}
	}