package br.com.trajy.graphql.exception;

import com.netflix.graphql.dgs.DgsComponent;
import graphql.execution.SimpleDataFetcherExceptionHandler;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class GraphQlExceptionHandler extends SimpleDataFetcherExceptionHandler {


}
