package br.edu.ifpb.ads.questao_08.strategy;

import br.edu.ifpb.ads.questao_08.entities.XGeneratior;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 19/03/2017, 18:45:25
 */
public class G_Strategy implements Strategy{

    @Override
    public int gerate(XGeneratior xGeneratior) {
        return (int) Math.round(3 * Math.pow(xGeneratior.getXValue(), 2) + 5);
    }
    
}
