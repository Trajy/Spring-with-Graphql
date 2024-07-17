package br.com.trajy.graphql.exception;

import graphql.execution.DataFetcherExceptionHandlerResult;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GraphQlExceptionUtils {

   public DataFetcherExceptionHandlerResult buildResult(Exception ex) {
       return DataFetcherExceptionHandlerResult.newResult()

               .build();
   }

}
