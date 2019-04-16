package com.spir.ggcycling.service.Impl;

import com.aliyun.oss.OSSClient;
import com.spir.ggcycling.bean.Comment;
import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.dao.CommentMapper;
import com.spir.ggcycling.dao.NewsMapper;
import com.spir.ggcycling.service.NewsService;
import com.spir.ggcycling.utils.JedisUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * created by spir
 * Date2019/4/11 Time 20:15
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;
    @Autowired
    CommentMapper commentMapper;

    /**
     * 上传的我的阿里云
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String saveImage(MultipartFile file) throws IOException {
        String bucketName = "ggcycling";
        //Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAIFdibm4wgRSl2";
        String accessKeySecret = "Wbz7aslv0kRyJaOyP5Q9lKoB02R36L";

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        //key是上传的该文件的唯一标识，通常需要加uuid（spir自己写的，防止上传的文件重名）
        String key = UUID.randomUUID().toString().replace("-","")+file.getOriginalFilename();


// 上传文件流。
        InputStream inputStream = file.getInputStream();
        ossClient.putObject(bucketName, key, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();

        return "https://"+bucketName +".oss-cn-hangzhou.aliyuncs.com/" + key;
    }


    @Override
    public boolean addNews(News news) {
        news.setCommentCount(0);
        news.setCreatedDate(new Date());
        news.setLikeCount(0);
        return 1 == newsMapper.insert(news);
    }



    /**
     * 用nosql数据库redis，采用set数据结构（不能保存重复元素）,map里的value是set集合
     * @param newsId key
     * @param userId value
     * @return
     */
    @Override
    public String like(String  newsId,String  userId) {
        Jedis jedis = JedisUtils.getJedisFromPool();
        Long sadd = jedis.sadd(newsId, userId);
        LoggerFactory.getLogger(getClass()).info("userId为：" + userId + "的用户赞了" + "newsId为：" + newsId);//打印日志
        Long scard = jedis.scard(newsId);//查询newsId对应的value里有多少个数据
        jedis.close();
        return String.valueOf(scard);
    }

    /**
     * 采用redise nosql数据库
     * @param newsId
     * @param userId
     * @return
     */
    @Override
    public String dislike(String newsId, String userId) {
        Jedis jedis = JedisUtils.getJedisFromPool();
        jedis.srem(newsId,userId);
        LoggerFactory.getLogger(getClass()).info("userId为：" + userId + "的用户踩了" + "newsId为：" + newsId);
        Long scard = jedis.scard(newsId);
        jedis.close();
        return String.valueOf(scard);
    }

    @Override
    public News querySingleNews(int newsId) {
        return newsMapper.querySingleNews(newsId);

    }

    @Override
    public String queryLikeCount(String  newsId,String  userId) {
        Jedis jedis = JedisUtils.getJedisFromPool();
        Long scard = jedis.scard(newsId);
        jedis.close();
        return String.valueOf(scard);
    }

    @Override
    public User queryUserByNewsId(int newsId) {
        return newsMapper.queryUserByNewsId(newsId);
    }


}
