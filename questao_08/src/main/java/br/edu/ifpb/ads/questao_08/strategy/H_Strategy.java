package br.edu.ifpb.ads.questao_08.strategy;

import br.edu.ifpb.ads.questao_08.entities.XGeneratior;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 18:45:11
 */
public class H_Strategy implements Strategy{

    @Override
    public int gerate(XGeneratior xGeneratior) {
        double x = Math.round(xGeneratior.getXValue());
        return 15 * (int) Math.round(Math.pow(x, x));
    }
    
}
