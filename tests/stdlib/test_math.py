import os
import sys
from unittest import expectedFailure

from ..utils import TranspileTestCase


class MathModuleTests(TranspileTestCase):

    #######################################################
    # sqrt
    def test_sqrt(self):
        self.assertCodeExecution("""
            import math
            print(math.sqrt(100.0))
            print(math.sqrt(10))
            """)

    @expectedFailure
    def test_sqrt_with_str(self):
        self.assertCodeExecution("""
            import math
            print(math.sqrt('100'))
            """)

    @expectedFailure
    def test_sqrt_with_negative_values(self):
        self.assertCodeExecution("""
            import math
            print(math.sqrt(-100))
            """)