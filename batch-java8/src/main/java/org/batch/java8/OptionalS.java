package org.batch.java8;

import java.util.Optional;

/**
 * batch-parent.org.batch.java8 <br/>
 * Created by PengRong on 2018/1/3. <br/>
 *
 * @author PengRong <br/>
 * @Description Optional 判定使用(${END})
 * @ClassName: ${CLASS}
 * @since 2018-01-03 10:12 <br/>
 */
public class OptionalS {
    public static void main(String[] args) {
        // ofNullable 方法会进行判定，参数是否为空，如果为Null，那么生成EMPTY空的optional对象；
        //不为空 则生成为value 指定值的的Optional 对象。
        Optional<String> optional = Optional.ofNullable(null);
        System.out.println(optional.isPresent());
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }

        Optional<String> optional_1 = Optional.ofNullable("asdfsdf");
        System.out.println(optional_1.isPresent());
        if (optional_1.isPresent()) {
            System.out.println(optional_1.get());
        }

        Optional<String> optional_2 = Optional.of("of");
        System.out.println(optional_2.isPresent());
        if (optional_2.isPresent())
            System.out.println(optional_2.get());
        if (optional_2.isPresent())
            System.out.println(optional_2.orElse("haha"));
        //这个会报错，因为of方法会进行null 校验；如果传入的参数为null；那么将会报空指针异常错误。
        /*Optional<String> optional_3=Optional.of(null);
        System.out.println(optional_3.isPresent());
        System.out.println(optional_3.orElse("hahaof"));
        if (optional_3.isPresent())
            System.out.println(optional_3.get());
*/
        Optional<String> optional_3 = Optional.ofNullable(null);
        System.out.println(optional_3.isPresent());
        System.out.println(optional_3.orElse("hahaof"));
        if (optional_3.isPresent())
            System.out.println(optional_3.get());


    }
}
