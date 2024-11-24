package model.garbage;

import model.values.IValue;
import model.values.IntValue;
import model.values.RefValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GarbageCollector {
    public static Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream().filter(v -> v instanceof RefValue)
                .map(v -> {
                        RefValue refValue = (RefValue) v;
                        return refValue.getAddress();
                    })
                .collect(Collectors.toList());
    }

    public static List<Integer> getAddrFromHeap(Map<Integer, IValue> heap) {
        return heap.values().stream()
                .filter(v->v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }

    public static Map<Integer, IValue> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        List<Integer> referencedAddresses = new ArrayList<>(symTableAddr);
        referencedAddresses.addAll(getAddrFromHeap(heap));
        return unsafeGarbageCollector(referencedAddresses, heap);
    }
}
