package mephi.listlab;

public class ListClass {
    public static class Elem
    {
        Object obj;
        int index;
        Elem next = null;

        public Elem(Object obj, int index)
        {
            this.obj = obj;
            this.index = index;
        }
    }

    public static class List
    {
        Elem head = null;

        private Elem findO(Object obj)
        {
            Elem ptr = this.head;
            while (ptr != null && ptr.obj != obj)
            {
                ptr = ptr.next;
            }
            if (ptr != null)
                return ptr;
            else
                return null;
        }

        public void add(Object obj)
        {
            Elem ptr = this.head;
            if (head != null)
            {
                while (ptr.next != null) ptr = ptr.next;
                Elem nptr = new Elem(obj, ptr.index+1);
                ptr.next = nptr;
                nptr.next = null;
            }
            else
            {
                head = new Elem(obj, 0);
                head.next = null;
            }

        }

        public void add(Object obj, int index)
        {
            Elem ptr = this.head;
            if (head != null)
            {
                if (index == 0) {
                    Elem n_ptr = new Elem(obj, index);
                    n_ptr.next = head;
                    while (this.head != null)
                    {
                        ++this.head.index;
                        this.head = this.head.next;
                    }
                    head = n_ptr;
                }
                else{
                    while (ptr.next != null && ptr.next.index != index) ptr = ptr.next;
                    Elem n_ptr = new Elem(obj, index);
                    if (ptr.next == null)
                    {
                        ptr.next = n_ptr;
                        n_ptr.next = null;
                    }
                    else
                    {
                        n_ptr.next = ptr.next;
                        ptr.next = n_ptr;
                        ptr = ptr.next.next;
                        while (ptr != null)
                        {
                            ++ptr.index;
                            ptr = ptr.next;
                        }
                    }

                }
            }
            else
            {
                head = new Elem(obj, 0);
                head.next = null;;
            }
        }

        public Object get(int index)
        {
            Elem ptr = this.head;
            while (ptr != null && ptr.index != index)
            {
                ptr = ptr.next;
            }
            if (ptr != null)
                return ptr.obj;
            else
                return null;
        }

        public Object remove(int index)
        {
            if (this.head != null)
            {
                if (this.head.index == index)
                {
                    Object buf = head.obj;
                    this.head = this.head.next;
                    System.gc(); // Сборщик мусора
                    Elem ptr = this.head;
                    while(ptr != null)
                    {
                        --ptr.index;
                        ptr= ptr.next;
                    }
                    return buf;
                }
                else
                {
                    Elem ptr = this.head;
                    while (ptr.next != null && ptr.next.index != index)
                    {
                        ptr = ptr.next;
                    }
                    if (ptr != null)
                    {
                        Object buf = ptr.next.obj;
                        ptr.next = ptr.next.next;
                        System.gc(); // Сборщик мусора
                        while(ptr != null)
                        {
                            ptr= ptr.next;
                            --ptr.index;
                        }
                        return buf;
                    }
                }
            }
            return null;
        }

        public Object set(Object obj, int index)
        {
            Elem ptr = this.head;
            while (ptr != null && ptr.index != index) ptr = ptr.next;
            if (ptr != null)
            {
                ptr.obj = obj;
                return obj;
            }
            else return null;
        }

        public boolean contains(Object obj)
        {

            if (findO(obj) != null)
                return true;
            else
                return false;
        }

        public int indexOf(Object obj)
        {
            Elem ptr = findO(obj);
            if (ptr != null)
                return ptr.index;
            else
                return -1;
        }

        public int SIZE()
        {
            Elem ptr = this.head;
            int i = 0;
            while (ptr != null)
            {
                ptr = ptr.next;
                ++i;
            }
            return i;
        }

        public boolean isEmpty()
        {
            if (this.head == null)
                return true;
            else
                return false;
        }
    }

    public static void main(String[] args)
    {
        List a = new List();
        a.add(-1);
        a.add("0", 1);
        a.add("1", 0);
        a.add("2", 1);
        a.add("3", 2);
        a.add("4", 3);
        a.add("5", 4);
        a.set(14, 4);
        a.add("6", 3);
        System.out.println(a.contains("1"));
        System.out.println(a.SIZE());
        System.out.println(a.isEmpty());
        System.out.println(a.get(0));
        System.out.println(a.remove(1));
        System.out.println(a.get(1));
        System.out.println(a.isEmpty());
    }
}
