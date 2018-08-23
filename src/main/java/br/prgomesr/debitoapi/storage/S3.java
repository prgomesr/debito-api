package br.prgomesr.debitoapi.storage;

import br.prgomesr.debitoapi.config.property.DebitoApiProperty;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Component
public class S3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    DebitoApiProperty property;

    public String salvarTemporariamente(MultipartFile file) {
        AccessControlList accessControlList = new AccessControlList();
        accessControlList.grantPermission(GroupGrantee.AllUsers, Permission.Read);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        String nomeUnico = gerarNomeUnico(file.getOriginalFilename());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    property.getS3().getBucket(), nomeUnico,
                    file.getInputStream(), objectMetadata)
                    .withAccessControlList(accessControlList);
            putObjectRequest.setTagging(new ObjectTagging(
                    Arrays.asList(new Tag("expirar", "true")) //desta forma o arquivo ficará temporário em 1 dia
            ));
            amazonS3.putObject(putObjectRequest);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Arquivo {} enviado com sucesso para o S3. ", file.getOriginalFilename());
            }
            return nomeUnico;
        } catch (IOException e) {
            throw new RuntimeException("Problema ao tentar enviar arquivo para o S3" + e.getMessage());
        }
    }

    public void salvar(String objeto) {
        SetObjectTaggingRequest setObjectTaggingRequest = new SetObjectTaggingRequest(
                property.getS3().getBucket(), objeto, new ObjectTagging(Collections.emptyList())
        );
        amazonS3.setObjectTagging(setObjectTaggingRequest);
    }

    public void remover(String objeto) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
                property.getS3().getBucket(), objeto
        );
        amazonS3.deleteObject(deleteObjectRequest);
    }

    public void substituir(String objetoAntigo, String objetoNovo) {
        if (StringUtils.hasText(objetoAntigo)) {
            this.remover(objetoAntigo);
        }
        salvar(objetoNovo);
    }

    public String configurarUrl(String objeto) {
        return "\\\\" + property.getS3().getBucket() +
                ".s3.amazonaws.com/" + objeto;
    }

    private String gerarNomeUnico(String originalFilename) {
        return UUID.randomUUID().toString() + "_" + originalFilename;
    }
}
