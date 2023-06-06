package PawDorableApp.lambda;

import PawDorableApp.utils.NullUtils;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaRequest<T> extends APIGatewayProxyRequestEvent {

    protected static final ObjectMapper MAPPER = new ObjectMapper();
    protected final Logger log = LogManager.getLogger();

    /**
     * Dserialize a T (aka 'requestClass`) from the body of the request.
     * @param requestClass The type that should be created from the body of this LambdaRequest
     * @return A new instance of T that contains data from the request body
     */
    public T fromBody(Class<T> requestClass) {
        log.info("fromBody");
        try {
//            log.info("----------------> here request {}", super.getBody());
            return MAPPER.readValue(super.getBody(), requestClass);

        } catch (JsonProcessingException e) {
            log.error(e);
            throw new RuntimeException(
                    String.format("Unable to deserialize %s from request body", requestClass.getSimpleName()),
                    e);
        }
    }

    /**
     * Use the given converter to create an instance of T from the request's query string.
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's query string
     */
    public T fromQuery(Function<Map<String, String>, T> converter) {
        Map<String, String> query = NullUtils.ifNull(super.getQueryStringParameters(), Map.of());
        return converter.apply(query);
    }

    /**
     * Use the given converter to create an instance of T from the request's path parameters.
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's path parameters
     */
    public T fromPath(Function<Map<String, String>, T> converter) {
        log.info("fromPath");
        Map<String, String> path = NullUtils.ifNull(super.getPathParameters(), Map.of());
        return converter.apply(path);
    }

    /**
     * Use the given converter to create an instance of T from the request's path parameters
     * and query string parameters.
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's path parameters
     */
    public T fromPathAndQuery(BiFunction<Map<String, String>, Map<String, String>, T> converter) {
        log.info("fromPathAndQuery");
        Map<String, String> path = NullUtils.ifNull(super.getPathParameters(), Map.of());
        Map<String, String> query = NullUtils.ifNull(super.getQueryStringParameters(), Map.of());
        return converter.apply(path, query);
    }
}