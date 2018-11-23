package eu.bufa.prodan.myapplication.rest;

public class BaseCommunicator {

   /* public <T> Function<Throwable, Publisher<? extends T>> refreshTokenAndRetryFlowable(Flowable<T> originalFlowable) {
        return throwable -> {
            if (ExceptionStatusCode.isTokenExpiredException(throwable)) {
                return ProfileComunicator.getInstance().refreshToken()
                        .concatMap(loginData -> {
                            CurrentUser.getInstance().setAccessToken(loginData.getToken());
                    return originalFlowable;
                });
            }
            return Flowable.error(throwable);
        };
    }

    public <T> Flowable<T> authFlowable(Flowable<T> flowable) {
        return flowable.onErrorResumeNext(refreshTokenAndRetryFlowable(flowable));
    }

    public <T> Maybe<T> authMaybe(Maybe<T> maybe) {
        return maybe.onErrorResumeNext(refreshTokenAndRetryMaybe(maybe));
    }

    public <T> Function<Throwable, MaybeSource<? extends T>> refreshTokenAndRetryMaybe(Maybe<T> originalMaybe) {
        return throwable -> {
            if (ExceptionStatusCode.isTokenExpiredException(throwable)) {
                return ProfileComunicator.getInstance().refreshTokenMaybe().concatMap(loginData -> {
                    CurrentUser.getInstance().setAccessToken(loginData.getToken());
                    return originalMaybe;
                });
            }
            return Maybe.error(throwable);
        };
    }*/
}
