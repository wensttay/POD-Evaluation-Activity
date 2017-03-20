package br.edu.ifpb.ads.questao_08.strategy;

import br.edu.ifpb.ads.questao_08.entities.XGeneratior;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 18:37:05
 */
public class F_Strategy implements Strategy{

    @Override
    public int gerate(XGeneratior xGeneratior) {
        Double netiveX = xGeneratior.getXValue() * (-1);
        return (int) Math.round(0.833 * Math.exp(netiveX));
    }
    
}
