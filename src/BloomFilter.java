import java.util.BitSet;
import java.util.Random;
import java.util.Iterator;

public class BloomFilter implements Cloneable {
    private BitSet hashes;
    private RandomInRange prng;
    private int k; // Number of hash functions 进行K次相互独立的哈希，一共得到K个值，将这K个值在bloom filter中对应的bit位置1
    private static final double LN2 = 0.6931471805599453; // ln(2)

    /**
     * Create a new bloom filter.
     * @param n number of bit every element will be disturbed
     * @param m Desired size of the container in bits
     **/
    public BloomFilter(int n, int m) {
        if (n <= 0) k = 1; //m是过滤器容量，n是一个element分配多少个bit，k表示每个bit进行几次独立的哈希获得
        else k = n;
        this.hashes = new BitSet(m); //建立二进制集合
        this.prng = new RandomInRange(m, k); //传入过滤器容量和进行的哈希次数
    }

    //增加元素
    public void add(Object o) {
        prng.init(o); //用将新要加入的element对prng进行init，就是改变prng对象中的随机数属性改变
        for (RandomInRange r : prng) hashes.set(r.value);
        //这边因为RandomInRange实现了Iterable接口所以可以进行循环，具体看该类实现的抽象函数，
        //next、hasnext、iterator构造函数
    }

    //如果bitset里面有该元素分配到的所有bit，说明该元素可能存在
    //如果里面有一个没有，说明一定不存在
    public boolean contains(Object o) {
        prng.init(o);
        for (RandomInRange r : prng)
            if (!hashes.get(r.value))
                return false;
        return true;
    }

    //清空过滤器
    public void clear() {
        hashes.clear();
    }

    //拷贝过滤器
    public BloomFilter clone() throws CloneNotSupportedException {
        return (BloomFilter) super.clone();
    }

    //创建一个特别的hash代表过滤器
    public int hashCode() {
        return hashes.hashCode() ^ k;
    }


    //查看过滤器的bitset是否一致。注意：即使两个过滤器contain的元素一致，也不代表是相等的。例如，过滤器容量不同，也是不想等的
    public boolean equals(BloomFilter other) {
        return this.hashes.equals(other.hashes) && this.k == other.k;
    }

    //合并另一个过滤器到当前过滤器中，合并完成，当前过滤器拥有两个过滤器中的所以元素
    public void merge(BloomFilter other) {
        //如果这两个过滤器分配元素的bit数量不一致或过滤器容量不一致，抛出异常
        if (other.k != this.k || other.hashes.size() != this.hashes.size()) {
            throw new IllegalArgumentException("Incompatible bloom filters");
        }
        this.hashes.or(other.hashes);
    }

    private class RandomInRange implements Iterable<RandomInRange>, Iterator<RandomInRange> {

        private Random prng;
        private int max; // Maximum value returned + 1
        private int count; // Number of random elements to generate
        private int i = 0; // Number of elements generated
        public int value; // The current value

        RandomInRange(int maximum, int k) {
            max = maximum;  //max赋值过滤器容量大小
            count = k; //count记录需要进行分配bit的次数
            prng = new Random(); //创建一个随机数字
        }

        //作用就是初始化（改变）一下random值，setseed特性，传入相同的参数，获得的随机数永远一致
        public void init(Object o) {
            prng.setSeed(o.hashCode()); //setseed主要作用是设置随机种子，以便生成的随机数更加无法预测
        }

        //实现了Iterable接口，使RandomInRange可以用for进行循环，必须实现iterator构造函数
        public Iterator<RandomInRange> iterator() {
            i = 0;
            return this;
        }

        //循环的时候，每一层循环进行i++进行，记录循环的次数,即已经为element分配了几个bit
        //并且更新一下随机数，作为这次分配bit值
        public RandomInRange next() {
            i++;
            value = prng.nextInt() % max;
            if (value<0) value = -value;
            return this;
        }

        //判断是否已经分配完，分配完了就不再循环
        public boolean hasNext() {
            return i < count;
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
