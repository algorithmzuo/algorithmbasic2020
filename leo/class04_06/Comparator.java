package leo.class04_06;

/**
 * @author Leo
 * @ClassName Comparator
 * @DATE 2020/11/27 10:22 上午
 * @Description 比较器
 * 实现comparator中的compare方法
 * 两个参数
 * 返回负数 第一个参数排在前面
 * 返回0,谁都可以,
 * 返回正数,第二个参数排在前面.
 * arrays[数组] TreeSet,TreeMap[有序表]
 * TreeMap 如果k两个对象一样,无法加入,不覆盖,[不加重复的K的]
 * 如果想都留着,用hashCode()
 * 优先级队列可以添加重复的K
 *
 */
public class Comparator {

    public static class Student{
        String name;
        Integer age;
        Integer id;

    }


    public static class MyComparator implements java.util.Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return 0;
        }
    }
}
