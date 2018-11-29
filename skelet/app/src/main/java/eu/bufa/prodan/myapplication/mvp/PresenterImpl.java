package eu.bufa.prodan.myapplication.mvp;

import io.reactivex.disposables.CompositeDisposable;

public class PresenterImpl implements BasePresenter {
    private CompositeDisposable mDisponsables = new CompositeDisposable();
    private SomeViewHeder view;

    public void getData(){
        //some data retriving

        view.someMethod();
    }


    public PresenterImpl(SomeViewHeder view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        if(!mDisponsables.isDisposed())
            mDisponsables.dispose();
        mDisponsables.clear();
    }

    public interface SomeViewHeder extends BaseView{
        void someMethod();
    }
}
