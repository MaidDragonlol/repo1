package com.example.springboot1;

import com.sun.net.httpserver.Authenticator;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController

public class Controller {

    /*@RequestMapping(value = "/name")
     *//*@RequestParam 注解配合 @RequestMapping 一起使用，可以将请求的参数同处理方法的参数绑定在一起。
    @RestController
    @RequestMapping("/home")
    public class IndexController {
        @RequestMapping(value = "/name")
        String getName(@RequestParam(value = "person", required = false) String personName) {
            return "Required element of request param";
        }id 这个请求参数被映射到了 thegetIdByValue() 这个处理方法的参数 personId 上
        在这段代码中，因为 required 被指定为 false，所以 getName() 处理方法对于如下两个 URL 都会进行处理：
        /home/name?person=xyz
        /home/name
    }*//*
    String getName(@RequestParam(value = "person", required = false) String personName) {
        return "Required element of request param";*/

    /*@GetMapping()
    String get() {
        return "Hello from get";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    String delete() {
        return "Hello from delete";
    }
    @RequestMapping(method = RequestMethod.POST)
    String post() {
        return "Hello from post";
    }
    @RequestMapping(method = RequestMethod.PUT)
    String put() {
        return "Hello from put";
    }
    @RequestMapping(method = RequestMethod.PATCH)
    String patch() {
        return "Hello from patch";
    }*///所有的处理处理方法会处理从这同一个 URL( /home)进来的请求, 但要看指定的 HTTP 方法是什么来决定用哪个方法来处理。 */
        /*@GetMapping("/person")
        public @ResponseBody
        ResponseEntity< String > getPerson() {
            return new ResponseEntity < String > ("Response from GET", HttpStatus.OK);
        }
    @GetMapping("/person/{id}")
    public @ResponseBody ResponseEntity < String > getPersonById(@PathVariable String id) {
        return new ResponseEntity < String > ("Response from GET with id " + id, HttpStatus.OK);
    }
    @PostMapping("/person")
    public @ResponseBody ResponseEntity < String > postPerson() {
        return new ResponseEntity < String > ("Response from POST method", HttpStatus.OK);
    }
    @PutMapping("/person")
    public @ResponseBody ResponseEntity < String > putPerson() {
        return new ResponseEntity < String > ("Response from PUT method", HttpStatus.OK);
    }
    @DeleteMapping("/person")
    public @ResponseBody ResponseEntity < String > deletePerson() {
        return new ResponseEntity < String > ("Response from DELETE method", HttpStatus.OK);
    }
    @PatchMapping("/person")
    public @ResponseBody ResponseEntity < String > patchPerson() {
        return new ResponseEntity < String > ("Response from PATCH method", HttpStatus.OK);
    }*/
    @Autowired
    private static  String SUCCESS = null;
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id2) {
        SUCCESS = "testPathVariable: " + id2;
        return SUCCESS;
    }
    /*@PathVariable:绑定路径中的占位符参数到方法参数变量中；只能绑定路径中的占位符参数，且路径中必须有参数。
    如果参数名与占位符一致，则可直接使用@PathVariable；如果不一致，则在@PathVariable( )括号内绑定占位符。*/
}






