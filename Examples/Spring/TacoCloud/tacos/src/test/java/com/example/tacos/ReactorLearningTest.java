package com.example.tacos;

import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

@Slf4j
public class ReactorLearningTest
{
    @Test
    public void shouldFluxFlowCorrect()
    {
        Flux<String> nameFlux = Flux.just("Ruslan", "Vladimir", "John", "Jimmy");

        StepVerifier.create(nameFlux)
            .expectNext("Ruslan")
            .expectNext("Vladimir")
            .expectNext("John")
            .expectNext("Jimmy")
            .verifyComplete();
    }
    
    @Test
    public void shouldFluxFlowFromArrayCorrect()
    {
        Flux<Integer> intFlux = Flux.fromArray(new Integer[] { 1, 2, 3 });

        StepVerifier.create(intFlux)
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .verifyComplete();
    }
    
    @Test
    public void shouldFluxFlowFromTreeSetCorrect()
    {
        Flux<Integer> intFlux = Flux.fromIterable(
                Arrays
                        .asList(34, 25, 12, 79)
                        .stream()
                        .collect(Collectors.toCollection(() -> new TreeSet<Integer>())));

        StepVerifier.create(intFlux)
                .expectNext(12)
                .expectNext(25)
                .expectNext(34)
                .expectNext(79)
                .verifyComplete();
    }
    
    @Test
    public void shouldFluxFlowRangeCorrect()
    {
        StepVerifier.create(Flux.range(1, 4))
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .verifyComplete();
    }
    
    @Test
    public void shoudlFluxFlowInternalCorrect()
    {
        StepVerifier.create(Flux.interval(Duration.ofSeconds(2)).take(4))
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .expectNext(3L)
                .verifyComplete();
    }
    
    @Test
    public void shouldFluxFlowsMergingCorrect()
    {
        StepVerifier.create(
                Flux.just("first", "second", "third")
                        .delayElements(Duration.ofMillis(500))
                        .mergeWith(
                                Flux.just("1", "2", "3")
                                        .delaySubscription(Duration.ofMillis(250))
                                        .delayElements(Duration.ofMillis(500))))
                .expectNext("first")
                .expectNext("1")
                .expectNext("second")
                .expectNext("2")
                .expectNext("third")
                .expectNext("3")
                .verifyComplete();
    }
    
    @Test
    public void shouldZipFluxesCorrect()
    {
        StepVerifier.create(
                Flux.just("first", "second", "third")
                        .zipWith(Flux.just(1, 2, 3)))
                .expectNextMatches(
                        t -> t.getT1().equals("first") && t.getT2().equals(Integer.valueOf(1)))
                .expectNextMatches(
                        t -> t.getT1().equals("second") && t.getT2().equals(Integer.valueOf(2)))
                .expectNextMatches(
                        t -> t.getT1().equals("third") && t.getT2().equals(Integer.valueOf(3)))
                .verifyComplete();

        StepVerifier.create(
                Flux.just("first", "second", "third")
                        .zipWith(Flux.just(1, 2, 3), (s, i) -> String.format("%s %d", s, i))

        )
                .expectNext("first 1")
                .expectNext("second 2")
                .expectNext("third 3")
                .verifyComplete();
    }
    
    @Test
    public void shouldFluxFirstCorrect()
    {
        StepVerifier.create(
                Flux.firstWithSignal(
                        Flux.just(4, 5, 6),
                        Flux.just(1, 2, 3).delaySubscription(Duration.ofMillis(250))))
                .expectNext(4)
                .expectNext(5)
                .expectNext(6)
                .verifyComplete();
    }
    
    @Test
    public void shouldFluxSkipCorrect()
    {
        StepVerifier.create(
                Flux.just(1, 2, 3)
                        .delayElements(Duration.ofMillis(250))
                        .skip(Duration.ofMillis(750)))
                .expectNext(3)
                .verifyComplete();
    }
    
    @Test
    public void shouldFluxFilterCorrect()
    {
        StepVerifier.create(
                Flux.just(1, 2, 3, 4, 5, 6)
                        .filter(i -> (i & 1) == 0))
                .expectNext(2)
                .expectNext(4)
                .expectNext(6)
                .verifyComplete();
    }
    
    @Test
    public void shouldFluxDistinctCorrect()
    {
        StepVerifier.create(
                Flux.just(1, 1, 1, 1).distinct())
                .expectNext(1)
                .verifyComplete();
    }
    
    @Test
    public void shouldFluxMapCorrect()
    {
        StepVerifier.create(
            Flux.just(1, 2, 3)
            .map(i -> Integer.toString(i * i))
        )
        .expectNext("1")
        .expectNext("4")
        .expectNext("9")
        .verifyComplete();
    }

    @Test
    public void shouldFluxFlatMapCorrect()
    {
        Map<Integer, String> pairs = new TreeMap<>();

        pairs.put(1, "first");
        pairs.put(2, "second");
        pairs.put(3, "third");

        StepVerifier.create(
                Flux.just(1, 2, 3)
                .flatMap(
                        i -> Mono.just(i).map(j -> pairs.get(j)).subscribeOn(Schedulers.parallel())
                )
        )
        .expectNextMatches(i -> pairs.containsValue(i))
        .expectNextMatches(i -> pairs.containsValue(i))
        .expectNextMatches(i -> pairs.containsValue(i))
                        .verifyComplete();

    }

    @Test
    public void shouldFluxBufferCorrect()
    {
            StepVerifier.create(
                            Flux.just(1, 3, 4, 2, 5).buffer(3))
                            .expectNext(Arrays.asList(1, 3, 4))
                            .expectNext(Arrays.asList(2, 5))
                            .verifyComplete();
    }
    
    @Test
    public void shouldFluxBufferAndFlatMapCorrect()
    {
            Flux.just("a", "bc", "def", "ghij", "kmnlo")
                            .buffer(3)
                            .flatMap(l -> Flux.fromIterable(l)
                                            .map(s -> s.toUpperCase()).subscribeOn(Schedulers.parallel()).log())
                            .subscribe();
    }
    
    @Test
    public void shouldFluxCollectMapCorrect()
    {
            StepVerifier.create(
                    Flux.just("first", "second", "third")
                    .collectMap(s -> s.charAt(0))
            )
            .expectNextMatches(m -> {

                return
                        (m.get('f') == "first") &&
                        (m.get('s') == "second") &&
                        (m.get('t') == "third");

            })
            .verifyComplete();
    }

    @Test
    public void shoudlFluxAllCriteriaCorrect()
    {
            StepVerifier.create(
                    Flux.just("first", "fifth", "third", "fouth")
                    .all(s -> s.contains("t"))
            )
            .expectNext(true)
                            .verifyComplete();
    }

    @Test
    public void shouldFluxAnyCriteriaCorrect()
    {
            StepVerifier.create(
                    Flux.just("first", "second", "third")
                    .any(s -> s.contains("n"))
            )
            .expectNext(true)
                            .verifyComplete();
    }
}