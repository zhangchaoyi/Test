package daily;

/**
 * Created by zcy on 18-3-12.
 * 深复制，对于对象的每个成员需要成员实现Cloneable方法，并且在父类clone中调用成员的clone()
 */
public class CloneTest {

    public static class Person implements Cloneable {
        private int state;
        private Head head;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Head getHead() {
            return head;
        }

        public void setHead(Head head) {
            this.head = head;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Person p1 = (Person) super.clone();
            p1.setHead((Head) getHead().clone());

            return p1;
        }
    }

    public static class Head implements  Cloneable {
        private Face face;

        public Face getFace() {
            return face;
        }

        public void setFace(Face face) {
            this.face = face;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Head h = (Head) super.clone();
            h.setFace((Face) getFace().clone());

            return h;
        }
    }

    public static class Face implements Cloneable {
        private String look;

        public String getLook() {
            return look;
        }

        public void setLook(String look) {
            this.look = look;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Face f1 =  (Face)super.clone();
            String s = new String(f1.getLook());
            f1.setLook(s);

            return f1;
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Face f = new Face();
        f.setLook("look");
        Head h = new Head();
        h.setFace(f);
        Person p = new Person();
        p.setHead(h);
        p.setState(1);


        Person p1 = (Person) p.clone();
        System.out.println(p1==p);
        System.out.println(p1.getHead() ==p.getHead());
        System.out.println(p1.getHead().getFace() ==p.getHead().getFace());
        System.out.println(p1.getHead().getFace().getLook() ==p.getHead().getFace().getLook());
    }

}
