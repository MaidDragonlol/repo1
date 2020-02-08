O ��Ŀ����ע��
1��@SpringBootApplication ע��

�鿴Դ��ɷ��֣�@SpringBootApplication��һ������ע�⣬������@SpringBootConfiguration��@EnableAutoConfiguration��@ComponentScan������ע�⡣

������ע������÷ֱ�Ϊ��

@SpringBootConfiguration:��ע��ǰ���������࣬���ע��̳���@Configuration�����Ὣ��ǰ����������һ��������@Beanע���ǵķ�����ʵ�����뵽srping�����У�����ʵ�������Ƿ�������

@EnableAutoConfiguration:���Զ����õ�ע�⣬���ע������������ӵ����jar�����һЩĬ�����ã�������΢��ʱ�����spring-boot-starter-web������jar��pom�������������û�Ĭ������springmvc ��tomcat��

@ComponentScan:ɨ�赱ǰ�������Ӱ��±�@Component��@Controller��@Service��@Repositoryע���ǵ��ಢ���뵽spring�����н��й����ȼ���<context:component-scan>��xml�����ļ��е������

���������£���3��ע��ᱻͬʱʹ�ã��������ʵ����������ע��ͱ����˰�װ����Ϊ��@SpringBootApplicationע�⡣

2��@ServletComponentScan:Servlet��Filter��Listener ����ֱ��ͨ�� @WebServlet��@WebFilter��@WebListener ע���Զ�ע�ᣬ����ͨ��ע��servlet �����������������Ĺ��ܶ������������ã������������ʹ�õ���filter��ʵ�֣��õ������ע�⡣

3��@MapperScan:spring-boot֧��mybatis�����һ��ע�⣬ͨ����ע��ָ��mybatis�ӿ����·����������ɶ�mybatis�ӿڵ�ɨ�衣

����@mapperע����һ�������ã���ͬ�ĵط���ɨ����ڲ�һ����@mapper��Ҫ����ÿһ��mapper�ӿ������档���Դ��������£������ڹ滮�ù���Ŀ¼֮��ͨ��@MapperScanע������·�����mapper�ӿڵ�ע�롣

���mybatis��Ӧ�齨����֮�󡣾Ϳ���ʹ�ø�ע�⡣
��һ���鿴mybatis-spring-boot-starter���������ҵ������Ѿ���mybatis���˰�װ��
��Ҳ��spring��һ��������ظ������ӣ������������Դ����spring����ϵ�С�
4����Դ����ע�⣺@ImportResource @Import @PropertySource ������ע�ⶼ�����������Զ����һЩ�����ļ���

@ImportResource(locations={}) ��������xml�����ļ�����Ҫ��׼�����������ϡ�

����property�������ļ� @PropertySourceָ���ļ�·��������൱��ʹ��spring��<importresource/>��ǩ���������������롣

@importע����һ�����Խ���ͨ�ർ�뵽spring������������

O controller ��

1��@Controller �����������һ���������࣬��@RequestMapping�����ʹ�����������������method��ע������ķ�ʽ��Ĭ��������get��post���������������ɺ�ת��һ����ͼ�������������ڴ��΢������ʱ��ǰ��˻������롣����������ֻ��ע���ݴ�����˷���json���ݵĻ�����Ҫ���@ResponseBodyע������ɡ�

����һ��ֻ��Ҫ�������ݵĽӿھ���Ҫ3��ע������ɣ����������Ƕ�����Ҫ�������ݡ�Ҳ�ǻ������ʵ�������Խ�������ע���һ�����ϡ�

@RestController ��@Controller ��@ResponseBody�Ľ�ϣ�һ���౻����@RestController ע�⣬���ݽӿ��оͲ�����Ҫ���@ResponseBody�����Ӽ�ࡣ

ͬ�������,@RequestMapping(value="",method= RequestMethod.GET ),���Ƕ���Ҫ��ȷ����ʽ��������д���ֻ��ԵñȽϷ��������Ǻ����������µļ���ע�⡣

 

��ͨ���	//Rest���
@RequestMapping(value=����,method = RequestMethod.GET)

@GetMapping(value =����)

@RequestMapping(value=����,method = RequestMethod.POST)

@PostMapping(value =����)

@RequestMapping(value=����,method = RequestMethod.PUT)

@PutMapping(value =����)

@RequestMapping(value=����,method = RequestMethod.DELETE)

@DeleteMapping(value =����)

�⼸��ע���� @RequestMapping(value="",method= RequestMethod.xxx )�����ʵ����Ϊ�˴���ĸ��Ӽ�ࡣ

2��@CrossOrigin:@CrossOrigin(origins = "", maxAge = 1000) ���ע����Ҫ��Ϊ�˽��������ʵ����⡣���ע�����Ϊ����controller�������ÿ���Ҳ�����ڷ����������á�

��������Ŀ��ʹ�����ע����Ϊ�˽��΢��������ʱ������ȱ��ŵ�ʱ�򣬻���ʲ�ͬ��spider�ڵ�����ֿ������⡣

3��@Autowired:���Ǹ�����Ϥ��ע�⣬��spring���Զ�װ�䣬�����ע������õ��������������򣬷�����ע�������ϡ���������Ҫ��bean �����л�ȡһ��beanʱ��Spring���Զ�Ϊ����װ���bean�б��Ϊ@Autowired��Ԫ�ء�

4��@EnablCaching@EnableCaching: ���ע����spring framework�е�ע�������Ļ�������ܡ���spring�汾3.1������˸�ע�⡣�������൱��spring�����ļ��е�cache manager��ǩ��

5��@PathVariable��·������ע�⣬@RequestMapping����{}������url���ֵı��������磺



ͬ������֧�ֱ�������������ʽ�ķ�ʽ��������:[������ʽ]��



O servcie��ע��

1��@Service�����ע���������ҵ������������ǻὫҵ���߼�������඼��������ע�⽻��spring���������������Ҳ����������һ�㡣���� ���ע�ⲻ��һ��Ҫ�á��и���ָ�����ע�⣬�����ǲ���ȷ���������õ�ʱ�� �����÷�ָ�����ע���и���spring������ 

2��@Resource��@Resource��@Autowiredһ������������װ��bean�������Ա�ע�ֶ��ϣ����߷����ϡ� @resourceע�ⲻ��spring�ṩ�ģ�������J2EE�淶��ע�⡣

����֮ǰ���������ƥ�䷽ʽ���е㲻ͬ��@ResourceĬ�ϰ������Ʒ�ʽ����beanƥ�䣬@AutowiredĬ�ϰ������ͷ�ʽ����beanƥ�䡣

O �־ò�ע��

1��@Repository��@Repositoryע������ΪDAO���󣬹���������ݿ�Ķ���

�ܵ�������@Component, @Service, @Controller, @Repository��springע�⣬ע�����Ա�spring�����ɨ�貢ע�뵽spring���������й���

@Component��ͨ��ע�⣬��������ע�������ע�����չ�����Ҿ������ض��Ĺ��ܡ�

ͨ����Щע��ķֲ�������ܽ������������߼��������ݿ����������������Ϊ������Ҳ�������Ժ���Ŀ��ά���Ϳ�����

�������������������У��������@Service, @Controller, @Repository����һ����ע�����Ķ�λ��ʱ�򣬾Ͳ�Ҫ��@Component����ע��

2��@Transactional�� ͨ�����ע������������񣬿�����������ϻ��߷����ϡ�

��spring boot�� �����ٵ��������������һ����������ǻ���servcie�����������ע�⣬���ɿ�������Ҫע����ǣ�����Ŀ���ֻ����public �����ϡ�������Ҫ��������Ļع�������������������rollbackfor exceptionʱ ������ڷ����ﲶ�����쳣�ͻᵼ�������������õ�ʧЧ��

O �������ע��

@ControllerAdvice �� @RestControllerAdvice��ͨ����@ExceptionHandler��@InitBinder��@ModelAttributeһ�����ʹ�á�

@ControllerAdvice �� @ExceptionHandler ������ͳһ�쳣���ش���

@RestControllerAdvice �� @ControllerAdvice �� @ResponseBody�ĺϼ������Խ��쳣��json�ĸ�ʽ�������ݡ�

������������쳣���ص�ͳһ����

