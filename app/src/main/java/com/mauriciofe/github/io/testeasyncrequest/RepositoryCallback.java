package com.mauriciofe.github.io.testeasyncrequest;

public interface RepositoryCallback<T> {
    void onComplete(T result);
}
