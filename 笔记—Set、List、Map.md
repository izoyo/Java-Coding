## 笔记—Set、List、Map

[TOC]



Java集合的主要分为三种类型：

- Set（集）
- List（列表）
- Map（映射）



有人想有可以自动扩展的数组，所以有了List。

有的人想有没有重复的数组，所以有了Set。

有人想有自动排序的数组，所以有了TreeSet、TreeList、TreeMap。



![](https://i.loli.net/2019/04/05/5ca7479fb4216.png)



- 元素顺序

Set特点：元素无放入顺序，元素不可重复，重复元素会覆盖掉

List特点：元素有放入顺序，元素可重复 

注意：元素虽然无放入顺序，但是元素在set中的位置是有该元素的HashCode决定的，其位置其实是固定的，加入Set 的Object必须定义equals()方法 ，另外list支持for循环，也就是通过下标来遍历，也可以用迭代器，但是set只能用迭代，因为他无序，无法用下标来取得想要的值。 



- 特点

Set：检索元素效率低下，删除和插入效率高，插入和删除不会引起元素位置改变。 

List：和数组类似，List可以动态增长，查找元素效率高，插入删除元素效率低，因为会引起其他元素位置改变。 

Map：适合储存键值对的数据



- 线程安全

LinkedList、ArrayList、HashSet是非线程安全的，Vector是线程安全的。

HashMap是非线程安全的，HashTable是线程安全的。

StringBuilder是非线程安全的，StringBuffer是线程安全的。



### Array

Java中有一个Arrays类，专门用来操作array。
arrays中拥有一组static函数：

- equals()：比较两个array是否相等。array拥有相同元素个数，且所有对应元素两两相等。
- fill()：将值填入array中。
- sort()：用来对array进行排序。
- binarySearch()：在排好序的array中寻找元素。
- System.arraycopy()：array的复制。



### 集合分类

**Collection**：List、Set

**Map**：HashMap、HashTable



![](https://i.loli.net/2019/04/05/5ca7493012b1a.png)



### Collection

Collection是最基本的集合接口，声明了适用于JAVA集合（只包括Set和List）的通用方法。 Set 和List 都继承了Conllection，Map。

```java
boolean add(Object o)		// 向集合中加入一个对象的引用 
 
void clear()				// 删除集合中所有的对象，即不再持有这些对象的引用 
 
boolean isEmpty()			// 判断集合是否为空 
 
boolean contains(Object o)	// 判断集合中是否持有特定对象的引用 
 
Iterartor iterator()		// 返回一个Iterator对象，可以用来遍历集合中的元素 
 
boolean remove(Object o)	// 从集合中删除一个对象的引用 
 
int size()					// 返回集合中元素的数目 
 
Object[] toArray()			// 返回一个数组，该数组中包括集合中的所有元素 

```

### Iterator接口

```java
hasNext()		// 判断集合中元素是否遍历完毕，如果没有，就返回true 
 
next()			// 返回下一个元素 
 
remove()		// 从集合中删除上一个有next()方法返回的元素。
```

- 遍历 ArrayList

```java 
import java.util.*;
 
public class Test{
 public static void main(String[] args) {
     List<String> list=new ArrayList<String>();
     list.add("Hello");
     list.add("World");
     list.add("HAHAHAHA");
     //第一种遍历方法使用foreach遍历List
     for (String str : list) {            //也可以改写for(int i=0;i<list.size();i++)这种形式
        System.out.println(str);
     }
 
     //第二种遍历，把链表变为数组相关的内容进行遍历
     String[] strArray=new String[list.size()];
     list.toArray(strArray);
     for(int i=0;i<strArray.length;i++) //这里也可以改写为  foreach(String str:strArray)这种形式
     {
        System.out.println(strArray[i]);
     }
     
    //第三种遍历 使用迭代器进行相关遍历
     
     Iterator<String> ite=list.iterator();
     while(ite.hasNext())//判断下一个元素之后有值
     {
         System.out.println(ite.next());
     }
 }
}
```

- 遍历 Map

```java
import java.util.*;
 
public class Test{
     public static void main(String[] args) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("1", "value1");
      map.put("2", "value2");
      map.put("3", "value3");
      
      //第一种：普遍使用，二次取值
      System.out.println("通过Map.keySet遍历key和value：");
      for (String key : map.keySet()) {
       System.out.println("key= "+ key + " and value= " + map.get(key));
      }
      
      //第二种
      System.out.println("通过Map.entrySet使用iterator遍历key和value：");
      Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
      while (it.hasNext()) {
       Map.Entry<String, String> entry = it.next();
       System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }
      
      //第三种：推荐，尤其是容量大时
      System.out.println("通过Map.entrySet遍历key和value");
      for (Map.Entry<String, String> entry : map.entrySet()) {
       System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }
    
      //第四种
      System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
      for (String v : map.values()) {
       System.out.println("value= " + v);
      }
     }
}
```



## Set

Set是最简单的一种集合。集合中的对象不按特定的方式排序，并且没有重复对象。 

Set接口主要实现了两个实现类：

​	HashSet： HashSet类按照哈希算法来存取集合中的对象，存取速度比较快 。

​	TreeSet ：TreeSet类实现了SortedSet接口，能够对集合中的对象进行排序。 



-  HashSet

此类实现 Set 接口，由哈希表（实际上是一个 HashMap 实例）支持。它不保证集合的迭代顺序；特别是它不保证该顺序恒久不变。集合元素可以是 null,但只能放入一个 null，存入 HashSet 的对象必须定义 hashCode()。

它不允许出现重复元素，不保证集合中元素的顺序。允许包含值为null的元素，但最多只能有一个null元素。HashSet的实现是不同步的。



- TreeSet

TreeSet 是 SortedSet 接口(Set 的子接口)的唯一实现类，TreeSet 可以确保集合元素处于排序状态，TreeSet 支持自然排序(Comparable)和定制排序(Comparator)。



？红-黑树

红黑树是平衡二叉查找树的一种。算法的规则: 左小右大。

参考：[红黑树深入剖析及Java实现](https://tech.meituan.com/2016/12/02/redblack-tree.html)



## List 

List 继承自 Collection 接口。List 是有序的 Collection，List 允许有重复元素，实现 List 接口的常用类有 LinkedList，ArrayList，Vector 和 Stack。

- ArrayList

ArrayList 实现了可变大小的数组。它允许所有元素，包括 null。ArrayList 没有同步.

特点：寻址容易，插入和删除困难。

- LinkedList

LinkedList实现了List接口，允许null元素。此外LinkedList提供额外的get，remove，insert方法在 LinkedList的首部或尾部。这些操作使LinkedList可被用作堆栈（stack），队列（queue）或双向队列（deque）。注意LinkedList没有同步方法。如果多个线程同时访问一个List，则必须自己实现访问同步。

特点：寻址困难，插入和删除容易。

- Vector

Vector 非常类似 ArrayList，但是 Vector 是同步的

- Stack 类

Stack继承自Vector，实现一个后进先出的堆栈。Stack提供5个额外的方法使得 Vector得以被当作堆栈使用。基本的push和pop 方法，还有peek方法得到栈顶的元素，empty方法测试堆栈是否为空，search方法检测一个元素在堆栈中的位置。Stack刚创建后是空栈。



**ArrayList**类提供了List ADT的一种可增长数组的实现。使用ArrayList的优点在于，对get和set的调用花费常数时间。其缺点是新项的插入和现有项的删除代价昂贵，除非变动是在ArrayList的末端是在ArrayList的末端运行。

**LinkedList**类则提供了List ADT的双链表实现。使用LinkedList的优点在于，新项的插入和现有项的删除均开销很小，这里假设变动项的位置是已知的。



## Map

Map 是一种把键对象和值对象映射的集合，它的每一个元素都包含一对键对象和值对象。 Map没有继承于Collection接口 从Map集合中检索元素时，只要给出键对象，就会返回对应的值对象。 

- Hashtable

Hashtable继承Map接口，实现一个key-value映射的哈希表。任何非空（non-null）的对象都可作为key或者value。Hashtable 是同步的(线性安全)。

添加数据使用put(key, value)，取出数据使用get(key)，这两个基本操作的时间开销为常数。

作为key的对象将通过计算其散列函数来确定与之对应的value的位置，因此任何作为key的对象都必须实现hashCode和equals方法。



- HashMap

HashMap 和 Hashtable 类似，不同之处在于 HashMap 是非同步的，并且允许 null，即 null value 和null key

对 HashMap 排序，用 Collections 中的方法

HashMap里面实现一个静态内部类Entry，其重要的属性有 key , value, next。

![](https://i.loli.net/2019/04/05/5ca758695013c.png)



- WeakHashMap类

WeakHashMap是一种改进的HashMap，它对key实行“弱引用”该key可能会被GC自动删除，即使程序员没有调用remove()或者clear()方法。

WeekHashMap 的这个特点特别适用于需要缓存的场景。

