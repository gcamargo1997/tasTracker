package br.com.tas.tracker.console.validators.impl;
import org.apache.commons.beanutils.BeanUtils;
import br.com.tas.tracker.console.validators.annotations.ConfirmSenhaEqualsValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * @author guilherme.camargo
 * @since 23/09/2018
 * @version 1.0
 * */
public class ConfirmSenhaEqualsValidator implements ConstraintValidator<ConfirmSenhaEqualsValid, Object> {
    private String senha;
    private String confirmSenha;
    private String message;
    @Override
    public void initialize(ConfirmSenhaEqualsValid constraintAnnotation) {
        this.senha = constraintAnnotation.senha();
        this.confirmSenha = constraintAnnotation.confirmSenha();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        try{
            final String senhaObj = BeanUtils.getProperty(o, senha);
            final String confirmSenhaObj = BeanUtils.getProperty(o, confirmSenha);
            result = senhaObj.equals(confirmSenhaObj);
        }catch (Exception e){
            result = false;
        }
        if(!result){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode("confirmSenha").addConstraintViolation();
        }
        return result;
    }

}
