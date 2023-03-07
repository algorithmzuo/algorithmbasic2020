package class07;

// 课上讲的包一层，这是不对的！
// 这个文件是废物！留在这里是为了提醒同学们 : 
// 看"Code03_ShowHeapGreater"，展示了用法
public class Inner<T> {
	public T value;

	public Inner(T v) {
		value = v;
	}
}
