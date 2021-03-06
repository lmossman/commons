/**
 *  Copyright 2013 Liveramp
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.liveramp.commons.collections.lightweight_trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TestStringRadixTreeMap {
  @Test
  public void testSize() throws Exception {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    assertEquals(0, map.size());
    assertTrue(map.isEmpty());
    map.put("blah", 1);
    assertEquals(1, map.size());
    assertFalse(map.isEmpty());

    // TODO:
//    map.put("blah", 2);
//    assertEquals(1, map.size());
  }

  @Test
  public void testGetPut() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    assertFalse(map.containsKey("blah"));
    assertNull(map.get("blah"));
    map.put("blah", 1);
    assertTrue(map.containsKey("blah"));
    assertEquals(Integer.valueOf(1), map.get("blah"));
  }

  @Test
  public void testClear() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    map.put("blah", 1);
    map.put("blah2", 2);

    map.clear();
    assertNull(map.get("blah"));
    assertNull(map.get("blah2"));
    assertTrue(map.isEmpty());
  }

  @Test
  public void testPutAll() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    map.putAll(new HashMap<String, Integer>(){{put("blah1", 1); put("blah2", 2);}});
    assertEquals(2, map.size());
    assertEquals(Integer.valueOf(1), map.get("blah1"));
    assertEquals(Integer.valueOf(2), map.get("blah2"));
  }

  @Test
  public void testLotsOfElements() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 50; i++) {
      sb.append("a");
      map.put(sb.toString(), i);
    }

    sb = new StringBuilder();
    for (int i = 0; i < 50; i++) {
      sb.append("a");
      assertEquals(Integer.valueOf(i), map.get(sb.toString()));
    }
  }

  @Test
  public void testReplaceValue() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    assertFalse(map.containsKey("blah"));
    assertNull(map.get("blah"));
    map.put("blah", 1);
    assertTrue(map.containsKey("blah"));
    assertEquals(Integer.valueOf(1), map.get("blah"));
    map.put("blah", 2);
    assertEquals(Integer.valueOf(2), map.get("blah"));
  }

  @Test
  public void testEntrySet() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    map.put("blah", 1);
    map.put("blah2", 2);
    map.put("foo", 7);
    map.put("bar", 15);
    map.put("LONGGGG one", 250);

    Map<String, Integer> otherMap = new HashMap<>();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      otherMap.put(entry.getKey(), entry.getValue());
    }

    Map<String, Integer> expectedMap = new HashMap<>();
    expectedMap.put("blah", 1);
    expectedMap.put("blah2", 2);
    expectedMap.put("foo", 7);
    expectedMap.put("bar", 15);
    expectedMap.put("LONGGGG one", 250);

    assertEquals(expectedMap, otherMap);
  }

  @Test
  public void testKeySet() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    map.put("blah", 1);
    map.put("blah2", 2);
    map.put("foo", 7);
    map.put("bar", 15);
    map.put("LONGGGG one", 250);

    Set<String> keys = new HashSet<>();
    for (String k : map.keySet()) {
      keys.add(k);
    }

    assertEquals(new HashSet<>(Arrays.asList("blah", "blah2", "foo", "bar", "LONGGGG one")), keys);
  }

  @Test
  public void testValues() {
    StringRadixTreeMap<Integer> map = new StringRadixTreeMap<>();
    map.put("blah", 1);
    map.put("blah2", 2);
    map.put("foo", 7);
    map.put("bar", 15);
    map.put("LONGGGG one", 250);

    assertEquals(new HashSet<>(Arrays.asList(1, 2, 7, 15, 250)), new HashSet<>(map.values()));
  }
}
