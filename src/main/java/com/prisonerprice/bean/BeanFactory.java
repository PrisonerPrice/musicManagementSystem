package com.prisonerprice.bean;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// IOC

@Configuration
public class BeanFactory {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 amazonS3() {
        return  AmazonS3ClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    /*
    DefaultAWSCredentialsProviderChain looks for credentials in this order:
    1. Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY ->  EnvironmentVariableCredentialsProvider
    2. Java System Properties - aws.accessKeyId and aws.secretKey -> SystemPropertiesCredentialsProvider
    3. Credential profiles file at the default location (~/.aws/credentials) shared by all AWS SDKs and the AWS CLI -> ProfileCredentialsProvider
    4. Credentials delivered through the Amazon EC2 container service if AWS_CONTAINER_CREDENTIALS_RELATIVE_URI" environment variable is set and security manager has permission to access the variable -> EC2ContainerCredentialsProviderWrapper
    5. Instance profile credentials delivered through the Amazon EC2 metadata service
    6. Web Identity Token credentials from the environment or container.
    */

    /*
     *  Copyright 2019, Liwei Wang <daveywang@live.com>.
     *  All rights reserved.
     *  Author: Liwei Wang
     *  Date: 06/2019
     */

}
