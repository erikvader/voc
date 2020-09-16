import os
import sys
import unittest

from ..utils import TranspileTestCase

class MathModuleTests(TranspileTestCase):
    def test_pow(self):
        self.assertCodeExecution("""
            import math
            print(math.pow(2.0, 2.0))
            print(math.pow(0.0, 2.0))
            print(math.pow(2.0, 0.0))
            print(math.pow(0.0, 0.0))
            print(math.pow(1.0, 2.0))
            print(math.pow(2.0, 1.0))
        """)

    @unittest.expectedFailure
    def test_pow_with_negative(self):
        self.assertCodeExecution("""
            import math
            print(math.pow(-2.0, 2.0)))
        """)

    @unittest.expectedFailure
    def test_pow_with_string(self):
        self.assertCodeExecution("""
            import math
            print(math.pow("string", 2.0))
        """)
