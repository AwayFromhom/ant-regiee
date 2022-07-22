package com.itheima.reggie.config;

//@Configuration
//public class JacksonConfig {
//
//    /**
//     * Jackson全局转化long类型为String，解决jackson序列化时long类型缺失精度问题
//     * @return Jackson2ObjectMapperBuilderCustomizer 注入的对象
//     */
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
//                .serializerByType(Long.class, ToStringSerializer.instance)
//                .serializerByType(Long.TYPE, ToStringSerializer.instance);
//    }
//}

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean(ObjectMapper.class)
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder)
//    {
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//
//        SimpleModule simpleModule = new SimpleModule();
//
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//
//        return objectMapper;
//    }
//}
//
//@Configuration
//class ResourcesConfig implements WebMvcConfigurer {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
//        // 坑2
//        // converters.add(jackson2HttpMessageConverter);
//        converters.add(0, jackson2HttpMessageConverter);
//    }
//}
// @EnableWebMvc   // 坑1
//@Configuration
//public class ResourcesConfig implements WebMvcConfigurer {
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
//        // 坑2
//        // converters.add(jackson2HttpMessageConverter);
//        converters.add(0, jackson2HttpMessageConverter);
//    }
//}

