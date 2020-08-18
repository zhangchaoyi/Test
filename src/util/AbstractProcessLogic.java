package util;

import functional.ExecutorFunction;

/**
 * @description:
 * @author: zhangchaoyi
 * @date: 2019/11/29
 */
public abstract class AbstractProcessLogic<IN, OUT> implements ExecutorFunction<IN, OUT> {

    @Override
    public OUT accept(IN in, OUT out) {
        if(!this.validate(in, out)){
            return out;
        }

        this.preProcess(in, out);

        this.processing(in, out);

        this.afterProcess(in, out);

        return out;
    }

    protected abstract boolean validate(IN in, OUT out);

    protected abstract void preProcess(IN in, OUT out);

    protected abstract void processing(IN in, OUT out);

    protected abstract void afterProcess(IN in, OUT out);

}
